package lesson2;

class Group {
    private int ind = -1;
    private Employee[] employees = new Employee[10];
    private String name;

    public Group(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        String employeesStr = "";
        for (int i = 0; i <= ind; ++i) {
            employeesStr += "\n" + employees[i].toString();
        }
        return "Group{" +
                "name='" + name + '\'' +
                "\nemployees=[" + employeesStr + "\n]" +
                "}";
    }

    public void add(Employee e) {
        if (ind == employees.length - 1) {
            throw new RuntimeException("Нет места для нового сотрудника");
        }
        ind++;
        employees[ind] = e;
    }

    public void clearAll() {
        ind = -1;
        employees = new Employee[10];
    }

    public void remove(int i) {
        if (i > ind) {
            return;
        }
        employees[i] = null;
        for (int j = i; j <= ind; ++j) {
            if (j + 1 < employees.length) {
                employees[j] = employees[j + 1];
            }
        }
        --ind;
    }
}

