package TestFile;

import java.sql.Connection;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;
import java.util.concurrent.ExecutionException;
import java.sql.*;
import TestFile.*;
public class Test {
	
	public static void main(String[] args) {
		Insert insert = new Insert();
		insert.ins(); //insert
		insert.sel(); //select
		insert.del(); //delete
		insert.up();  //update
		
		
	}

}
