package servlets;

import java.io.BufferedWriter;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.Base64;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import package1.Item;

/**
 * Servlet implementation class Cart
 */
@WebServlet("/Cart")
public class Cart extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Item[] items;
	private Item item;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Cart() {
		super();
		// TODO Auto-generated constructor stub
	}

	public void init(ServletConfig config) throws ServletException {
		items = new Item[100];
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		if (request.getParameter("add") != null) {
			String string = request.getParameter("add");

			try {
				byte b[] = Base64.getUrlDecoder().decode(string);
				ByteArrayInputStream bi = new ByteArrayInputStream(b);
				ObjectInputStream si = new ObjectInputStream(bi);
				item = (Item) si.readObject();
				si.close();
			} catch (Exception e) {
				System.out.println(e + " cart class");
			}
			int index = putToArray();
			items[index] = item;
			HttpSession session = request.getSession(true);
			session.setAttribute("items_in_cart", items);
			response.sendRedirect("html/hub/cart.jsp");
		}
		if (request.getParameter("delete") != null) {
			String string = request.getParameter("delete");

			try {
				byte b[] = Base64.getUrlDecoder().decode(string);
				ByteArrayInputStream bi = new ByteArrayInputStream(b);
				ObjectInputStream si = new ObjectInputStream(bi);
				item = (Item) si.readObject();
				si.close();
			} catch (Exception e) {
				System.out.println(e + " cart class");
			}
			deleteFromArray(item);
			HttpSession session = request.getSession(true);
			session.removeAttribute("items_in_cart");
			session.setAttribute("items_in_cart", items);
			response.sendRedirect("html/hub/cart.jsp");
		}

		if (request.getParameter("firstname") != null && request.getParameter("lastname") != null
				&& request.getParameter("email") != null && request.getParameter("phonenumber") != null) {
			String firstname = request.getParameter("firstname");
			String lastname = request.getParameter("lastname");
			String email = request.getParameter("email");
			String phonenumber = request.getParameter("phonenumber");
			try {
				FileWriter fileWriter = new FileWriter("check.txt");
				BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
				int x = 0;
				int counter = 0;
				float total = 0.0f;
				bufferedWriter.write("Credentials:" + "\r\n");
				bufferedWriter.write("===============" + "\r\n");
				bufferedWriter.write("Order for " + firstname + " " + lastname + "\r\n");
				bufferedWriter.write("email: " + email + "\r\n");
				bufferedWriter.write("phone number: " + phonenumber + "\r\n");
				bufferedWriter.write("===============" + "\r\n");
				while (x < items.length) {
					if (items[x] != null) {
						counter++;
						total = total + items[x].getCost();
						bufferedWriter.write("Item " + counter + ":" + "\r\n");
						bufferedWriter.write("===============" + "\r\n");
						bufferedWriter.write("Title: " + items[x].getTitle() + "\r\n");
						bufferedWriter.write("Price: $" + items[x].getCost() + "\r\n");
						bufferedWriter.write("===============" + "\r\n");
					}
					x++;
				}
				bufferedWriter.write(String.format("Total: $%.2f", total) + "\r\n");
				bufferedWriter.close();
			} catch (FileNotFoundException ex) {
				System.out.println("Unable to open file");
			} catch (IOException ex) {
				System.out.println("Error reading file");
			}
			HttpSession session = request.getSession();
			items = new Item[100];
			session.setAttribute("items_in_cart", items);
			response.sendRedirect("html/hub/cart.jsp");
		}

		if (request.getParameter("clear") != null && request.getParameter("clear").equals("yes")) {
			HttpSession session = request.getSession();
			items = new Item[100];
			session.setAttribute("items_in_cart", items);
			response.sendRedirect("html/hub/cart.jsp");
		}

	}

	public Item[] getItems() {
		return items;
	}

	private int putToArray() {
		if (items != null) {
			int x = 0;
			while (x < items.length) {
				if (items[x] == null) {
					return x;
				}
				x++;
			}
		}
		return 0;
	}

	private void deleteFromArray(Item item) {
		int x = 0;
		while (x < items.length) {
			if (items[x] != null && items[x].getId() == item.getId()) {
				items[x] = null;
				break;
			}
			x++;
		}
	}

}
