package edu.eci.arsw.blueprints;

import edu.eci.arsw.blueprints.config.AppConfig;
import edu.eci.arsw.blueprints.model.*;
import edu.eci.arsw.blueprints.services.BlueprintsServices;
import edu.eci.arsw.blueprints.persistence.BlueprintNotFoundException;
import edu.eci.arsw.blueprints.persistence.BlueprintPersistenceException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Set;

public class Main {
    public static void main(String[] args) {
        ApplicationContext ctx = new AnnotationConfigApplicationContext(AppConfig.class);
        BlueprintsServices services = ctx.getBean(BlueprintsServices.class);

        try {
            Blueprint bp1 = new Blueprint("Alice", "PlanA", new Point[]{new Point(10, 10), new Point(20, 20)});
            Blueprint bp2 = new Blueprint("Alice", "PlanB", new Point[]{new Point(15, 15), new Point(25, 25)});
            Blueprint bp3 = new Blueprint("Bob", "PlanX", new Point[]{new Point(5, 5), new Point(8, 8)});

            services.addNewBlueprint(bp1);
            services.addNewBlueprint(bp2);
            services.addNewBlueprint(bp3);

            System.out.println("Blueprint Alice, PlanA: " + services.getBlueprint("Alice", "PlanA"));

            Set<Blueprint> aliceBlueprints = services.getBlueprintsByAuthor("Alice");
            System.out.println("Blueprints by Alice:");
            aliceBlueprints.forEach(System.out::println);

            Set<Blueprint> allBlueprints = services.getAllBlueprints();
            System.out.println("All blueprints in the system:");
            allBlueprints.forEach(System.out::println);

            System.out.println("Eliminando el blueprint PlanB de Alice...");
            services.deleteBlueprint("Alice", "PlanB");

            System.out.println("Blueprints by Alice después de eliminar PlanB:");
            aliceBlueprints = services.getBlueprintsByAuthor("Alice");
            aliceBlueprints.forEach(System.out::println);

        } catch (BlueprintNotFoundException e) {
            System.err.println("Blueprint no encontrado: " + e.getMessage());
        }
    }
}

