package com;

import java.sql.*;

public class Supply {
	
	private Connection connect() {
		Connection con = null;
		try	{
			Class.forName("com.mysql.jdbc.Driver");
			
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/supplymanagement",
					"root", "rusiru");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return con;
	}
	
	public String insertItem(String supID, String itemName, String qty, String price, String total, String date) { 
        String output = ""; 
        try { 
            Connection con = connect(); 
            if (con == null) {
                return "Error while connecting to the database for inserting."; 
            } 

            // create a prepared statement
            String query = " insert into supplyitems " +
                            "(`ID`,`sup_id`,`item_name`,`qty`,`price`,`total`,`date`)"
                            + " values (?, ?, ?, ?, ?, ?, ?)"; 

            PreparedStatement preparedStmt = con.prepareStatement(query); 

            // binding values
            preparedStmt.setInt(1, 0); 
            preparedStmt.setString(2, supID); 
            preparedStmt.setString(3, itemName); 
            preparedStmt.setInt(4, Integer.parseInt(qty)); 
            preparedStmt.setDouble(5, Double.parseDouble(price)); 
			preparedStmt.setDouble(6, Double.parseDouble(total)); 
            preparedStmt.setString(7, date); 

            // execute the statement
            preparedStmt.execute(); 
            con.close(); 
            String newSupply = readPayments();
            output = "{\"status\":\"success\", \"data\": \"" + newSupply + "\"}"; 

        } catch (Exception e) { 
        	
        	output = "{\"status\":\"error\", \"data\":\"Error while inserting the supply.\"}";


            System.err.println(e.getMessage()); 
        } 
        
        return output; 
    }
	
	public String readPayments() {
		String output = ""; 

		try { 

			Connection con = connect(); 
			if (con == null) {
				return "Error while connecting to the database for reading."; 
			} 

			// Prepare the html table to be displayed
			output = "<table border='1'><tr>"+
			"<th>supplierID</th>"+
			"<th>Item Name</th>" +
			"<th>Quantity</th>" + 
			"<th>Price</th>" +
			"<th>Total</th>" + 
			"<th>Date</th>" +
			"<th>Update</th><th>Remove</th></tr>"; 
	 
			String query = "select * from supplyitems"; 
			Statement stmt = con.createStatement(); 
			ResultSet rs = stmt.executeQuery(query); 

			// iterate through the rows in the result set
			while (rs.next()) { 

				String supID = rs.getString("sup_id"); 
				String itemName = rs.getString("item_name"); 
				String qty = rs.getString("qty"); 
				String price = rs.getString("price"); 
				String total = rs.getString("total"); 
				String date = rs.getString("date"); 

				// Add into the html table
				output += "<tr><td><input id='hidSupIDUpdate'name='hidSupIDUpdate' type='hidden' value='" + supID + "'>"
						+ itemName + "</td>";
			
			    output += "<td>" + qty + "</td>"; 
				output += "<td>" + price + "</td>";
				output += "<td>" + total + "</td>"; 
				output += "<td>" + date + "</td>";				

				// buttons
				output += "<td><input name='btnUpdate' type='button' value='Update' "
						+ "class='btnUpdate btn btn-secondary' data-supid='" + supID + "'></td>"
						+ "<td><input name='btnRemove' type='button' value='Remove' "
						+ "class='btnRemove btn btn-danger' data-supid='" + supID + "'></td></tr>";
			} 
			con.close(); 

			// Complete the html table
			output += "</table>"; 

		} catch (Exception e) { 
			output = "Error while reading the items."; 
			System.err.println(e.getMessage()); 
		} 

		return output; 
	}
	
	public String updateItem(String ID, String supID, String itemName, String qty, String price, String total, String date) { 

        String output = ""; 

        try { 

            Connection con = connect(); 

            if (con == null) {
                return "Error while connecting to the database for updating."; 
            } 

            // create a prepared statement
            String query = "UPDATE supplyitems SET sup_id=?, item_name=?, qty=?, price=?, total=?, date=? WHERE ID=?"; 
            PreparedStatement preparedStmt = con.prepareStatement(query); 

            // binding values
            preparedStmt.setString(1, supID); 
            preparedStmt.setString(2, itemName); 
            preparedStmt.setInt(3, Integer.parseInt(qty)); 
            preparedStmt.setDouble(4, Double.parseDouble(price));
			preparedStmt.setDouble(5, Double.parseDouble(total)); 
            preparedStmt.setString(6, date);
            preparedStmt.setString(7, ID);

            // execute the statement
            preparedStmt.execute(); 
            con.close(); 
            String newSupply = readPayments();
            output = "{\"status\":\"success\", \"data\": \"" + newSupply + "\"}"; 

        } catch (Exception e) { 
        	
        	output = "{\"status\":\"error\", \"data\":\"Error while updating the item.\"}";

 
            System.err.println(e.getMessage()); 
        } 

        return output; 
    }
	
	public String deleteItem(String itemID) { 

        String output = ""; 

        try { 

            Connection con = connect(); 

            if (con == null) {
                return "Error while connecting to the database for deleting."; 
            }

            // create a prepared statement
            String query = "delete from supplyitems where ID=?"; 
            PreparedStatement preparedStmt = con.prepareStatement(query); 

            // binding values
            preparedStmt.setInt(1, Integer.parseInt(itemID)); 

            // execute the statement
            preparedStmt.execute(); 
            con.close(); 
            String newSupply = readPayments();
            output = "{\"status\":\"success\", \"data\": \"" + newSupply + "\"}"; 

        } catch (Exception e) { 
        	output = "{\"status\":\"error\", \"data\":\"Error while deleteing the supply.\"}"; 
            System.err.println(e.getMessage()); 
        } 

        return output; 
    } 
		
}

