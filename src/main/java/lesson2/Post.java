package lesson2;

enum Post {
    Programmer("Программист"), QA("Тестировщик"), Manager("Менеджер"), Lead("Начальник");
    private String fullName;

    public String getFullName() {
        return fullName;
    }

    Post(String fullName) {
        this.fullName = fullName;
    }
}
