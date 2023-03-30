package streamapi;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StreamMain1 {
	
	public static void main(String[] args) {
		
		List<Integer>list1=List.of(2, 4, 5, 6, 15, 25, 26, 27, 29, 30);
      System.out.println(list1);
      
      
      List<Integer> list2=new ArrayList<>();
      list2.add(10);
      list2.add(13);
      list2.add(15);
      list2.add(16);
      list2.add(21);
      
	System.out.println(list2);
	
	List<Integer> list3 =Arrays.asList(2,5, 12, 14, 50, 60,47);
	
	
	//list1
	
	//without stream 
	 List<Integer> listEven= new ArrayList<>();
	 
	 
	 for(Integer i:list1) {
		 if(i%2==0) {
			 listEven.add(i);
		 }
	 }
	System.out.println(list1);
	System.out.println(listEven);
	
	
	//using streamapi
	
	
	
	
	Stream<Integer> stream= list1.stream();
	List<Integer>newList=stream.filter(i -> i%2==0).collect(Collectors.toList());
	System.out.println(newList);
	
	
	List<Integer>newList2 =list1.stream().filter(i -> i%2==0).collect(Collectors.toList());
	
	System.out.println(newList2);
	
	List<Integer> list4 = list1.stream().filter(i ->i>10).collect(Collectors.toList());
	System.out.println(list4);
	
	
	//5 list,set
	
	List<Integer> list5  = new ArrayList();
	
	list5.add(45);
	list5.add(14);
	list5.add(74);
	list5.add(45);
	list5.add(98);
	Stream<Integer> steram2=list5.stream();
	
	
	
	}

}
