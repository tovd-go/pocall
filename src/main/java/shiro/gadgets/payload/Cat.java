package shiro.gadgets.payload;

final public class Cat {
        private String name = "catalina";

        public String getName() {
            System.out.println("this is the Get Method");
            return name;
        }

        public void setName(String name) {
            System.out.println("this is the Set Method");
            this.name = name;
        }
    }