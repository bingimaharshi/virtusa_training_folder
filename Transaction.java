public class Member {
    private int memberId;
    private String name;
    private String email;
    private String phone;

    public Member(int memberId, String name, String email, String phone) {
        this.memberId = memberId;
        this.name = name;
        this.email = email;
        this.phone = phone;
    }

    public int getMemberId() { return memberId; }
    public String getName() { return name; }
    public String getEmail() { return email; }
    public String getPhone() { return phone; }

    @Override
    public String toString() {
        return String.format("[ID: %d] %-25s | %s | %s", memberId, name, email, phone);
    }
}
