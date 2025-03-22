package com.example.JournalApplication;


import com.example.JournalApplication.service.UserService1;
import org.junit.jupiter.api.BeforeAll;


import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import org.springframework.beans.factory.annotation.Autowired;



import static org.junit.jupiter.api.Assertions.*;



class JournalApplicationTests {

	@Autowired
	public UserService1 userService1;
//
@Disabled
@Test
	public void addition(){
	int addition1= userService1.add(1,2);
	assertEquals(3,addition1);

}
@Disabled
@BeforeAll
public static void hellio(){
 System.out.println("hello si");
}

@Disabled
@ParameterizedTest
	@CsvSource({
			"1,1,2",
			"2,2,4",
			"2,2,2"
	})
	public void tes(int a,int b,int excepted){
	assertEquals(excepted,a+b);
}}


