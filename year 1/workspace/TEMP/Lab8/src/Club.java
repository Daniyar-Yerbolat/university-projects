import java.util.ArrayList;

public class Club {
	// Define any necessary fields here ...
	private ArrayList<Membership> member;

	public Club() {
		member = new ArrayList<Membership>();
	}

	public void join(Membership member) {
		this.member.add(member);
	}

	public int numberOfMembers() {
		return member.size();
	}

	public static void main(String[] args) {
		Club club = new Club();
		club.join(new Membership("Michael", 1, 2004));
		club.join(new Membership ("David", 2, 2004));
		System.out.println("The club has " + club.numberOfMembers() + " members.");
	}
}
