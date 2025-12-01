package BackEnd;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ValidationResult {
    private final List<String> rowErrors = Collections.synchronizedList(new ArrayList<>());
    private final List<String> colErrors = Collections.synchronizedList(new ArrayList<>());
    private final List<String> boxErrors = Collections.synchronizedList(new ArrayList<>());
    
    private boolean isValid = true;

    public void addError(String type, String errorMsg) {
        isValid = false;
        switch (type) {
            case "ROW":
                rowErrors.add(errorMsg);
                break;
            case "COL":
                colErrors.add(errorMsg);
                break;
            case "BOX":
                boxErrors.add(errorMsg);
                break;
        }
    }

    public void printResults() {
        if (isValid) {
            System.out.println("VALID");
        } else {
            System.out.println("INVALID");
            printSortedList(rowErrors);
            if (!rowErrors.isEmpty() && (!colErrors.isEmpty() || !boxErrors.isEmpty())) {
                System.out.println("------------------------------------------");
            }
            printSortedList(colErrors);
            if (!colErrors.isEmpty() && !boxErrors.isEmpty()) {
                System.out.println("------------------------------------------");
            }
            printSortedList(boxErrors);
        }
    }

    private void printSortedList(List<String> list) {
        synchronized (list) {
            Collections.sort(list); 
            for (String err : list) {
                System.out.println(err);
            }
        }
    }
}