public class AddContract {
    public static class AddInputContract{
        public int id;
        public String name;
        public String date;
        public Status status;

        public void setId(int id) {
            this.id = id;
        }

        public void setName(String name) {
            this.name = name;
        }

        public void setDate(String date) {
            this.date = date;
        }

        public void setStatus(Status status) {
            this.status = status;
        }

        public int getId() {
            return id;
        }

        public String getName() {
            return name;
        }

        public String getDate() {
            return date;
        }

        public Status getStatus() {
            return status;
        }
    }
    public static class AddOutputContract{
        public int id;
        public String name;
        public String date;
        public Status status;

        public int getId() {
            return id;
        }

        public String getName() {
            return name;
        }

        public String getDate() {
            return date;
        }

        public Status getStatus() {
            return status;
        }

        public void setId(int id) {
            this.id = id;
        }

        public void setName(String name) {
            this.name = name;
        }

        public void setDate(String date) {
            this.date = date;
        }

        public void setStatus(Status status) {
            this.status = status;
        }
    }

}
