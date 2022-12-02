import java.io.FileNotFoundException;

public class app {
    public static void main(String[] args) {
        // example
        CSVReader degreePlanData = new CSVReader("data/DegreePlan.csv");
        try {
            String[][] degreePlanDataArray = degreePlanData.readTo2DArray();
            for (String[] row : degreePlanDataArray) {
                for (String ele : row) { System.out.print(ele + " ");}
                System.out.println();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
