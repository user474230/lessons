package lesson2;

class Employee {
    private String name;
    private String email;
    private int age;
    private Post post;

    Employee() {
        this("", "", 0, Post.QA);
    }

    Employee(String name, String email, int age, Post post) {
        this.name = name;
        this.email = email;
        this.age = age;
        this.post = post;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }

    @Override
    public String toString() {
        return "Сотридник{" +
                "имя= '" + name + '\'' +
                ", email= '" + email + '\'' +
                ", возраст= " + age +
                ", должность= " + post.getFullName() +
                "}";
    }
}

