import java.util.Scanner;

public class ROICalculator {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Input data
        System.out.print("Enter manual testing effort (hours): ");
        double manualTestingHours = scanner.nextDouble();

        System.out.print("Enter automation testing effort (hours): ");
        double automationTestingHours = scanner.nextDouble();

        System.out.print("Enter hourly rate for manual testers: ");
        double manualTesterRate = scanner.nextDouble();

        System.out.print("Enter hourly rate for automation testers: ");
        double automationTesterRate = scanner.nextDouble();

        System.out.print("Enter one-time setup costs for automation testing: ");
        double setupCosts = scanner.nextDouble();

        // Calculating the costs
        double totalManualTestingCost = manualTestingHours * manualTesterRate;
        double totalAutomationTestingCost = (automationTestingHours * automationTesterRate) + setupCosts;
        double netWorthOfAutomation = totalManualTestingCost - totalAutomationTestingCost;

        // Calculating ROI
        double roi = (netWorthOfAutomation / totalManualTestingCost) * 100;

        // Display the results
        System.out.println("\n--- ROI Calculation Results ---");
        System.out.printf("Total Cost of Manual Testing: $%.2f\n", totalManualTestingCost);
        System.out.printf("Total Cost of Automation Testing: $%.2f\n", totalAutomationTestingCost);
        System.out.printf("Net Worth of Automation Testing: $%.2f\n", netWorthOfAutomation);
        System.out.printf("ROI: %.2f%%\n", roi);

        scanner.close();
    }
}