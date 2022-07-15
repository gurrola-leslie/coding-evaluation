package com.aa.act.interview.org;
import java.util.Optional;
import java.util.*;

public abstract class Organization {

	private Position root;
	
	public Organization() {
		root = createOrganization();
	}
	
	protected abstract Position createOrganization();
	
	/**
	 * hire the given person as an employee in the position that has that title
	 * 
	 * @param person
	 * @param title
	 * @return the newly filled position or empty if no position has that title
	 */
	public Optional<Position> hire(Name person, String title) {
            
            //creating a potential employee
            Random rand = new Random();
            int intRandom = rand.nextInt(10000);
            //random number generater 
            Employee potentialEmployee = new Employee(intRandom, person);
            Optional<Position> posPosition = positionFinder(title);
            
            
            //checking of positon is empty or if no position has that title
            if(posPosition.isEmpty()){
                //return original statement
                return Optional.empty();
            }
            //set employee to positon in set
            //Optional of() return an optional with specified present non-null value
            posPosition.get().setEmployee(Optional.of(potentialEmployee));
            
            return posPosition;
            
        }
        
        
        //sees if position if filled or not 
        //created a linked list to add position of new and look over positions
        
        public Optional<Position> positionFinder(String title)
        {
            Position head = root; 
            boolean isOpen = false; 
            LinkedList<Position> positionAva = new LinkedList(); 
            
            if(!positionAva.isEmpty()){
                
                positionAva.add(head);
            }
            
            if(head.getTitle().equals(title) && !head.isFilled()){
                isOpen = true;
            }
            
            if(!isOpen){
                //original return statement
                return Optional.empty();
            
            }
            
            return Optional.of(head);
        
        }
	@Override
	public String toString() {
		return printOrganization(root, "");
	}
	
	private String printOrganization(Position pos, String prefix) {
		StringBuffer sb = new StringBuffer(prefix + "+-" + pos.toString() + "\n");
		for(Position p : pos.getDirectReports()) {
			sb.append(printOrganization(p, prefix + "\t"));
		}
		return sb.toString();
	}
}
