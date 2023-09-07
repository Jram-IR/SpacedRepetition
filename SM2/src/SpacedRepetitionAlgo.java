public class SpacedRepetitionAlgo {
    public static class ReviewResult {
        private final boolean graduated;
        private final int timeMinutes;
        private final double easinessFactor;
        public ReviewResult(boolean graduated, int timeMinutes, double easinessFactor) {
            this.graduated = graduated;
            this.timeMinutes = timeMinutes;
            this.easinessFactor = easinessFactor;
        }
        public boolean isGraduated() { return  graduated; }
        public int getTimeMinutes() {
            return timeMinutes;
        }
        public double getEasinessFactor() { return easinessFactor; }
    }
    public static ReviewResult reviewCard(boolean graduated, String learningTag, String previousLearningTag, int previousMinutes, double previousEasinessfactor) {
        // Define the intervals for each learning tag
        int againInterval = 1; // Minutes
        int hardInterval = 10; // Minutes
        int goodInterval = 60; // Minutes
        int easyInterval = 1440; // Minutes (1 day)


        // Calculate the next review time based on the learning tag and previous time
        int nextInterval = switch (learningTag) {
            case "Again" -> againInterval;
            case "Hard" -> hardInterval;
            case "Good" -> goodInterval;
            case "Easy" -> easyInterval;
            default -> againInterval;
        };

        // Default easiness factor
        double easinessFactor = 2.5;

        // Calculate the time in minutes until the next review
        int timeMinutes = nextInterval;

        //first time graduation

        if (!graduated && learningTag.equals("Easy") && previousLearningTag.equals("Easy")) {

            graduated = true;

            double q = 4.0; // value of q for easy

            // Update the easiness factor based on your formula and the calculated q
            easinessFactor += (0.1 - (5 - q) * (0.08 + (5 - q) * 0.02));

            nextInterval = (int) (previousMinutes * easinessFactor);

            // Update the timeMinutes with the new interval
            timeMinutes = nextInterval;
        }

        //already graduated

        else if (graduated) {
            // Calculate the value of q based on the user rating
            double q = switch (learningTag) {
                case "Again" -> 1.0;
                case "Hard" -> 2.0;
                case "Good" -> 3.0;
                case "Easy" -> 4.0;
                default -> 0.0;
            };
            // Update the easiness factor based on formula and the calculated q
            easinessFactor = previousEasinessfactor + (0.1 - (5 - q) * (0.08 + (5 - q) * 0.02));

            // Ensure that the easiness factor remains within the specified range
            easinessFactor = Math.max(easinessFactor, 1.3);

            nextInterval = (int) (previousMinutes * easinessFactor);

            // Update the timeMinutes with the new interval
            timeMinutes = nextInterval;
        }

        // Return the results
        return new ReviewResult(graduated, timeMinutes, easinessFactor);
    }
}
