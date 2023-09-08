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




        // NOT GRADUATED //




        // Define the intervals for each learning tag when not graduated

        int againInterval = 1; // Minutes
        int hardInterval = 3; // Minutes
        int goodInterval = 5; // Minutes
        int easyInterval = 10; // Minutes

        // Calculate the next review time based on the learning tag and previous time
        int nextInterval = switch (learningTag) {
            case "Again" -> againInterval;
            case "Hard" -> hardInterval;
            case "Good" -> goodInterval;
            case "Easy" -> easyInterval;
            default -> againInterval;
        };

        // Calculate the time in minutes until the next review
        int timeMinutes = nextInterval;

        // Default easiness factor
        double easinessFactor = 2.5;




        // GRADUATED //




        // card graduates after 2 consecutive "Easy" user rating

        if (!graduated && learningTag.equals("Easy") && previousLearningTag.equals("Easy")) {

            graduated = true;

            // Update the timeMinutes with 1 day
            timeMinutes = 1440;
        }




        // graduated card becomes false after "Again" user rating

        else if(graduated && learningTag.equals("Again")) {

            graduated = false;
        }



        //already graduated

        else if (graduated) {

            // Calculate the value of q based on the user rating
            double q = switch (learningTag) {
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
