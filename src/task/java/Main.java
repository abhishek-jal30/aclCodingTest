package task.java;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;


public class Main {
	public static void main(String[] args) throws IOException {
		while (true) {
			performAction(getInput());
		}

	}

	static int getInput() {
		System.out.println("Please select one of the following:");
		System.out.println("1.List all tasks.");
		System.out.println("2.Add new task.");
		System.out.println("3.Update existing task.");
		System.out.println("4.Delete existing task.");
		System.out.println("5.Exit.");
		Scanner sc = new Scanner(System.in);
		int option = sc.nextInt();
		return option;
	}

	static void performAction(int actionId) throws IOException {
		switch (actionId) {
		case 1:
			getAllTask();
			break;
		case 2:
			addNewTask();
			break;
		case 3:
			updateExistingTask();
			break;
		case 4:
			deleteExistingTask();
			break;
		case 5:
			System.exit(0);
			break;
		default:
			System.out.println("Unsupported Operation");

		}
	}

	private static void deleteExistingTask() throws IOException {
		System.out.println("Please Enter the task number to delete");
		Scanner sc = new Scanner(System.in); // System.in is a standard input stream
		int option = sc.nextInt();
		ArrayList<String> taskList = JsonHandler.read();
		if (option - 1 <= taskList.size()) {
			if (taskList.size() != 0) {
				int len = taskList.size();
				for (int i = 0; i < len; i++) {
					if (option - 1 == i) {
						taskList.remove(option - 1);
					}
				}
			}
		} else {
			System.out.println("Invalid task number");
		}
		JsonHandler.write(taskList);
	}

	private static void updateExistingTask() throws IOException {
		System.out.println("Please Enter the task number to update");
		Scanner sc1 = new Scanner(System.in);
		int option = sc1.nextInt();
		System.out.println("Please Enter new task");
		Scanner sc2 = new Scanner(System.in);
		String newTask = sc2.nextLine();
		ArrayList<String> taskList = JsonHandler.read();
		if (newTask.length() >= 3 && newTask.length() < 30) {
			if (taskList.size() != 0) {
				int len = taskList.size();
				for (int i = 0; i < len; i++) {
					if (i == option - 1) {
						taskList.set(option - 1, newTask);
					}
				}
			} else {
				System.out.println("There is no taks list... Please add Task to get");
			}
		} else {
			System.out.println("Invalid input... Please Enter value greater than 3 char and less than 30 char");
		}
		System.out.println("Task Updated Successfully !!");
		JsonHandler.write(taskList);

	}

	private static void addNewTask() throws IOException {
		System.out.println("Please Enter new task");
		Scanner sc2 = new Scanner(System.in);
		String newTask = sc2.nextLine();
		ArrayList<String> taskList = JsonHandler.read();
		if (taskList.size() == 100) {
			System.out.println("100 Task is already added");
			System.exit(0);
		}
		if (newTask.length() >= 3 && newTask.length() < 30) {
			if (taskList.size() != 0) {
				int len = taskList.size();
				for (int i = 0; i < len; i++) {
					if (i == len - 1) {
						taskList.add(newTask);
					}
				}
			} else if (taskList.size() == 0) {
				int len = taskList.size();
				taskList.add(newTask);
			} else {
				System.out.println("Invalid input");
			}
			System.out.println("Task Added successfully!!");
		} else {
			System.out.println("Invalid input... Please Enter value greater than 3 char and less than 30 char");
		}
		JsonHandler.write(taskList);
	}

	private static void getAllTask() throws IOException {
		ArrayList<String> taskList = JsonHandler.read();
		if (taskList.size() != 0) {
			int len = taskList.size();
			for (int i = 0; i < len; i++) {
				System.out.println(i + 1 + ". " + taskList.get(i));
			}
		} else {
			System.out.println("There is no taks list... Please add Task to get");
		}
	}

}
