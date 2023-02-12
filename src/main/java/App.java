
import services.ClientService;

public class App {
    public static void main(String[] args) {
        //new MigrationService().migrate();
        ClientService clientService = new ClientService();
        //System.out.println("clientService.create(\"Danylo\") = " + clientService.create("Danylo"));
        // System.out.println("clientService.getById(6) = " + clientService.getById(6));
        // clientService.setName(1,"Alex");
        // clientService.deleteById(7);
         System.out.println("clientService.listAll() = " + clientService.listAll());

    }
}
