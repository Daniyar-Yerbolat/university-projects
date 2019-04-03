package lab6;
public class LoveLetter {
	private MailServer server;
	private MailClient sophie;
	private MailClient juan;
	
	public LoveLetter ()
	{
		server = new MailServer ();
		sophie = new MailClient (server, "sophie");
		juan = new MailClient (server, "juan");
	}
	
	public void start()
	{
		sophie.sendMailItem("juan", "hi");
		juan.printNextMailItem();
		
	}
	
	public static void main (String[]args)
	{
		LoveLetter text = new LoveLetter();
		text.start();
	}
}
