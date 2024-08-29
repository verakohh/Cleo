package cleo.task;
public class ToDos extends Task {
        protected String by;

        public ToDos(String description) {
            super(description);
        }

        /**
         * Returns the string description of the task.
         * @return the string description of the task in the format "[T] description".
         */
        @Override
        public String toString() {
            return "[T] " + super.toString();
        }
}

