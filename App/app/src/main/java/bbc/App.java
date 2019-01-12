package bbc;

import static spark.Spark.*;

import dto.MachineDTO;
import model.enums.gpus.GpuTier;
import model.enums.machines.MachineSubTier;
import model.enums.machines.MachineTier;
import model.machines.Machine;
import repositories.MachineRepository;
import services.GPUService;
import services.MachineService;
import spark.Request;
import spark.Response;
import spark.Route;

import static bbc.JsonUtil.*;

public class App {
    public static void main(String[] args) {
        port(8888);

        MachineService machineService = new MachineService();
        GPUService gpuService = new GPUService();

        options("/*", (request, response) -> {

            String accessControlRequestHeaders = request.headers("Access-Control-Request-Headers");
            if (accessControlRequestHeaders != null) {
                response.header("Access-Control-Allow-Headers", accessControlRequestHeaders);
            }

            String accessControlRequestMethod = request.headers("Access-Control-Request-Method");
            if (accessControlRequestMethod != null) {
                response.header("Access-Control-Allow-Methods", accessControlRequestMethod);
            }

            return "OK";
        });

        before((request, response) -> {
            response.header("Access-Control-Allow-Origin", "*");
            response.type("application/json");
        });

        

        get("/api/machines/get", (req, res) -> MachineDTO.convert(machineService.getMachines()), json());
        post("/api/machines/create", (req, res) -> {
            MachineTier tier = MachineTier.valueOf(req.queryParams("tier"));
            MachineSubTier subTier = MachineSubTier.valueOf(req.queryParams("subTier"));
            GpuTier gpuTier = req.queryParams("gpuTier").equals("") ? null : GpuTier.valueOf(req.queryParams("gpuTier"));
            int gpuCount = Integer.parseInt(req.queryParams("gpuCount"));

            Machine mac = machineService.createMachine(tier, 
                                        subTier, 
                                        gpuTier, 
                                        gpuCount);

            return new MachineDTO(mac);
        }, json());

        get("/api/machines/start/:id", (req, res) -> {
            int id = Integer.parseInt(req.params(":id"));
            machineService.startMachine(id);
            Machine mac = MachineRepository.getInstance().getById(id);

            return new MachineDTO(mac);
        }, json());

        get("/api/machines/stop/:id", (req, res) -> {
            int id = Integer.parseInt(req.params(":id"));
            machineService.stopMachine(id);
            Machine mac = MachineRepository.getInstance().getById(id);

            return new MachineDTO(mac);
        }, json());

        get("/api/machines/restart/:id", (req, res) -> {
            int id = Integer.parseInt(req.params(":id"));
            machineService.restartMachine(id);
            Machine mac = MachineRepository.getInstance().getById(id);

            return new MachineDTO(mac);
        }, json());

        get("/api/machines/mac/:id", (req, res) -> {
            int id = Integer.parseInt(req.params(":id"));
            Machine mac = MachineRepository.getInstance().getById(id);

            return new MachineDTO(mac);
        }, json());
    }
}
