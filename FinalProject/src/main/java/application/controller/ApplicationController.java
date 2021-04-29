package application.controller;

import application.model.facades.*;
import application.view.InventoryView;

public class ApplicationController {
	
	private LoginController loginController;
	private InventoryController inventoryController;
	
	public void manageInventory(Session session) {
		inventoryController = new InventoryController(session);

		InventoryView invView = new InventoryView(inventoryController);
		inventoryController.setView(invView);
		inventoryController.display();
	}
	
	public void login() {
		loginController = new LoginController(this);
		loginController.display();
	}
	
	
	public static void main(String[] args) {
		ApplicationController app = new ApplicationController();
		app.login();
	}
}