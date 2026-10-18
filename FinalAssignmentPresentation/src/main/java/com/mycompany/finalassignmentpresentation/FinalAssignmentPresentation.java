/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.finalassignmentpresentation;

import java.util.ArrayList;
import javax.swing.JOptionPane;

//class for Book entity
class Book{
    private String title;
    private String author;
    private String isbn;
    private boolean status; 
    
    //method for creating new book by inserting title, author and isbn number
    public Book(String title, String author, String isbn) {
        this.title = title;
        this.author = author;
        this.isbn = isbn;
        this.status = true;
    }
    
    //getter method to view book title, author, isbn number, status and entire book message
    public String getTitle() {
        return title;
    }
    public String getAuthor() {
        return author;
    }
    public String getIsbn() {
        return isbn;
    }
    public boolean getStatus() {
        return status;
    }
    public String getBook() {
        return ("Title: " + title + "\nAuthor: " + author +"\nISBN: " + isbn + "\nStatus: " + ((status==true)?"Available":"Not Available"));
    }
    
    //setter method for adjusting or updating the feature of the book
    public void setTitle(String title) {
        this.title = title;
    }
    public void setAuthor(String author) {
        this.author = author;
    }
    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }
    public void setStatus(boolean status) {
        this.status = status;
    }
}
//only allow Member class to access the User class
abstract class User{
    //name and faculty are protected to allow subclass to access directly
    protected String name;
    protected String faculty;
    
    //initialize name and faculty
    public User(String name, String faculty){
        this.name=name;
        this.faculty=faculty;
    }
    public abstract String getDetails();//every subclass must implement this method
}
//class for Member extends from user
class Member extends User{
    private String studentID;
    private int borrowedBooks;
    private ArrayList<Book> borrowedBookList;
    
    //method to create new member by inserting name, faculty and student ID. It will display the number of borrowed books as well
    public Member(String name, String faculty, String studentID){
        super(name,faculty); //set name and faculty in User
        this.studentID = studentID;
        this.borrowedBooks = 0;
        this.borrowedBookList=new ArrayList<>();
    }
    
    //getter methods to view member's name, faculty, student ID and the number of books borrowed
    public String getName(){
        return name;
    }
    
    public String getFaculty(){
        return faculty;
    }
    
    public String getStudentID(){
        return studentID;
    }
    
    public int getBorrowedBooks(){
        return borrowedBooks;
    }
    
    public String getMember(){
        return ("Name: "+ name + "\nFaculty: " + faculty + "\nStudent ID: " + studentID + "\nBorrowed Books: " + borrowedBooks);
    }
    
    @Override
    public String getDetails(){
        return getMember();//return member's details
    }
    
    //get method on displaying the title of borrowed books
    public String getBorrowedBookTitles(){
        //check if list is empty
        if(borrowedBookList.isEmpty()){
            return "No books borrowed.";
        }
        String titles="";
        //loop through the borrowed book list to insert book titles
        for(Book borrow:borrowedBookList){
            titles+="Title: "+borrow.getTitle()+"\n";
        }
        return titles;
    }
    //return list of borrowed book by member
    public ArrayList<Book> getborrowedBookList(){
        return borrowedBookList;
    }
    
    //setter method for adjusting or updating the member info
    public void setName(String name){
        this.name = name;
    }
    
    public void setFaculty(String faculty){
        this.faculty = faculty;
    }
    
    public void setStudentID (String studentID){
        this.studentID = studentID;
    }
    
    public void setBorrowedBooks (int count){
        this.borrowedBooks = count;//set total numbers of borrowed books
    }
}
public class FinalAssignmentPresentation {
    public static void main(String[] args) {
        
        //create an ArrayList to store the Book in library
        ArrayList<Book> libraryBook = new ArrayList<>();
        //create an ArrayList to store the member info in library
        ArrayList<Member> libraryMember = new ArrayList<>();
        //create an ArrayList to store borrowed book list in library
        ArrayList<Book> borrowedBookList = new ArrayList<>();
        String menu="Welcome to library management system!\n1. Book Management\n2. Member Management\n3. Borrow & Return Book\n4. Exit\nPlease choose what you want to do(1-4): ";
        
        String choice;
        //do-while loop for repeating ask user for choosing operation unless user enter 4 to exit the program.
        do{
            //ask user to choose the operation from book management, member management, borrow & return book and exit
            choice=JOptionPane.showInputDialog(null,menu,"Menu",JOptionPane.QUESTION_MESSAGE);
            //if user click " x " or "cancel", the system will assume it as option "4"
            if (choice == null) {
                choice = "4";
            }
            switch(choice){
                //BOOK MANAGEMENT
                case "1":
                    bookManagement(libraryBook);//call book management method
                    break;
                case "2":
                    memberManagement(libraryMember); //call member management method
                    break;
                case "3":
                    borrowReturnBook(libraryBook,libraryMember);
                    break;
                //pop up goodbye message and exit the program
                case "4":
                    JOptionPane.showMessageDialog(null, "Thank you. See you next time!");
                    break;
                //handling the input that others than 1-4 and show error message
                default:
                    JOptionPane.showMessageDialog(null, "Please choose the option between 1 to 4.","Error",JOptionPane.ERROR_MESSAGE);
            }
        }while(!"4".equals(choice));//loop the method until user input "4"  
    }
    
//to search for a member based on the student ID
public static Member findMember(ArrayList<Member> memberLibrary, String studentID){
//for loop to compare the student ID with the input of member's student id
    for (Member member : memberLibrary) {
        //check if the current member is in the list
        if (member.getStudentID().equals(studentID)) {    
            //if found, return the member object
            return member;
        }
    }
    //if no matching number if found, return null
    return null;
}

//method to check whether the student ID already exists
public static boolean DuplicateMember(ArrayList<Member> memberLibrary, String studentID){
    //for loop to compare all existing member student IDs with the input student ID
    for(Member member : memberLibrary){
        if(member.getStudentID().equals(studentID)){
            return true; //return true if duplicate is found
        }
    }
    return false; //return false if no duplicate is found
}
    
//method for finding book from the arraylist by using ISBN
public static Book findBook(ArrayList<Book> bookLibrary, String isbn) {
    Book temp=null;
    //for loop to compare tthe ISBN with the input ISBN for each book in the arraylist
    for (Book book : bookLibrary) {
        if (book.getIsbn().equals(isbn)) {
            //if the book  with that ISBN is founded and status is available, then this book detail will direct return
            if(book.getStatus()==true){
                return book;
            }else{
                temp=book; //if the book status is not available, then record it first and continue find is there available book with same ISBN
            }
        }
    }
    //if only find one book with that ISBN which is not available, then return that book detail
    //if the book witth that ISBN is not founded, null will be returned
    return temp;
}
//method for finding borrowed book from stuent ID and book ISBN
private static Book findBorrowedBook(Member borrowMember, String isbn) {
    //loop the book array to compare the ISBN with the input ISBN in the arraylist
    for (Book book : borrowMember.getborrowedBookList()) {
        //check if the ISBN book entered same as in the book array list
        if (book.getIsbn().equals(isbn)) {
            return book; //if exist return the details of book
        }
    }
    return null;//if not found will return null
}

//method for viewing book details from book list based on book ISBN
private static void viewBookDetails(ArrayList<Book>libraryBook,String isbn) {
    //declare total, available, borrowed, title and author
    int total=0;
    int available=0;
    int borrowed=0;
    String title="";
    String author="";
    //for loop to compare tthe ISBN with the input ISBN for each book in the arraylist
    for (Book book : libraryBook) {
        //check if ISBN of book in the array same as the input
        if(book.getIsbn().equals(isbn)) {
            total++;//increase total of book
            title=book.getTitle();//call getTitle method and put in title
            author=book.getAuthor();//call getAuthor method and put in author
            //check book status
            if(book.getStatus()){
                available++;//if available then increase
            }
            else{
                borrowed++;//if not available increase borrowed books
            }
        }
    }
    if(total==0){ //if total is 0
        //display message
        JOptionPane.showMessageDialog(null,"Book not Found!","View Book Details",JOptionPane.ERROR_MESSAGE);
    }
    else{
        //display details of books
        JOptionPane.showMessageDialog(null,"Title: "+title+"\nAuthor: "+author+
                "\nTotal Books: "+total+"\nAvailable Books: "+available+
                "\nBorrowed Books: "+borrowed,"View Book Details",JOptionPane.PLAIN_MESSAGE);
    }
}

//method for book management which include add book, update book, delete book and search book
private static void bookManagement(ArrayList<Book> libraryBook){
    String cBook; //shortform for choice for book management
    String bookMenu="Please choose what you want to do(1-5):\n1. Add Book\n2. Update Book\n3. Delete Book\n4. Search Book\n5. Back to Main Page";
        
    //do-while loop for repeating let user choose the operation unless user enter 4 to exit the program.
    do{
        //ask user to choose the operation from add book, update book, delete book, search book and back to main page
        cBook=JOptionPane.showInputDialog(null,bookMenu,"Book Management Menu",JOptionPane.QUESTION_MESSAGE);
        //if user click " x " or "cancel", the system will assume it as option "5"
        if (cBook == null) {
            cBook = "5";
        }
        //switch to user chosen operation
        switch(cBook){
            //ADD BOOK
            case "1":
                //ask user to input the title for new book
                String tempT=JOptionPane.showInputDialog(null,"Please enter the information for new book\n\nTitle: ","New Book",JOptionPane.QUESTION_MESSAGE);
                //ask user to input the author for new book
                String tempA=JOptionPane.showInputDialog(null,"Please enter the information for new book\n\nAuthor: ","New Book",JOptionPane.QUESTION_MESSAGE);
                //ask user to input the ISBN for new book
                String tempI=JOptionPane.showInputDialog(null,"Please enter the information for new book\n\nISBN: ","New Book",JOptionPane.QUESTION_MESSAGE);
                //create the new book and add into the arraylist
                libraryBook.add(new Book(tempT,tempA,tempI));
                //show successful message
                JOptionPane.showMessageDialog(null,"Book Added!", "New Book",JOptionPane.PLAIN_MESSAGE);
                break;
            //UPDATE BOOK
            case "2":
                //ask user to input the ISBN for the book that they want to update
                String tempIS=JOptionPane.showInputDialog(null,"Please enter the ISBN of the book for updating: ","Update Book",JOptionPane.QUESTION_MESSAGE);
                //call findbook method to find the book based on the ISBN user input and then store the result in "result"
                Book result = findBook(libraryBook, tempIS);
                //if the book founded:
                if(result!=null){
                    String choiceU;//shortform for choice for update book
                    //do-while loop for repeating let user choose the operation unless user enter 4 to back to the book management page.
                    do{
                        //show the menu which include founded book informations and the operation can be choose
                        String updateMenu=result.getBook()+"\n\nPlease choose what you want to update(1-4):\n1. Title\n2. Author\n3. ISBN\n4. Back to Book Management Page";
                        //ask user to choose the operation from update title, update author, update ISBN and back to book management page
                        choiceU=JOptionPane.showInputDialog(null,updateMenu,"Update Book",JOptionPane.QUESTION_MESSAGE);
                        //if user click " x " or "cancel", the system will assume it as option "4"
                        if(choiceU==null){
                            choiceU="4";
                        }
                        //switch to user chosen operation
                        switch(choiceU){
                            //UPDATE BOOK TITLE
                            case "1":
                                //ask user to enter the new title to update book title
                                String tempTi=JOptionPane.showInputDialog(null,"Please enter your new title: ","Update Book",JOptionPane.QUESTION_MESSAGE);
                                //set the title for founded book by calling setter method in book class
                                result.setTitle(tempTi);
                                break;
                            //UPDATE BOOK AUTHOR
                            case "2":
                                //ask user to enter the new author to update book author
                                String tempAu=JOptionPane.showInputDialog(null,"Please enter your new author: ","Update Book",JOptionPane.QUESTION_MESSAGE);
                                //set the author for founded book by calling setter method in book class
                                result.setAuthor(tempAu);
                                break;
                            //UPDATE BOOK ISBN
                            case "3":
                                //ask user to enter the new ISBN to update book ISBN
                                String tempIs=JOptionPane.showInputDialog(null,"Please enter your new ISBN: ","Update Book",JOptionPane.QUESTION_MESSAGE);
                                //set the ISBN for founded book by calling setter method in book class
                                result.setIsbn(tempIs);
                                break;
                            //BACK TO BOOK MANAGEMENT PAGE
                            case "4":
                                break;
                            //handling the input that others than 1-4 and show error message
                            default:
                                JOptionPane.showMessageDialog(null, "Please choose the option between 1 to 4.","Error",JOptionPane.ERROR_MESSAGE);
                        }
                    }while(!"4".equals(choiceU));//loop the method until user input "4"
                }else{
                    //if the book not founded, it will output error message
                    JOptionPane.showMessageDialog(null, "Sorry, the book with ISBN = "+tempIS+" is not founded.","Error",JOptionPane.ERROR_MESSAGE);
                }
                break;
            //DELETE BOOK
            case "3":
                //ask user to input the ISBN for the book that they want to delete
                String dIsbn=JOptionPane.showInputDialog(null,"Please enter the ISBN of the book you want to delete:", "Delete Book", JOptionPane.QUESTION_MESSAGE);
                //if user click " x ", the system will assume it back to previous page
                if(dIsbn==null){
                    break;
                }
                //call findbook method to find the book based on the ISBN user input and then store the result in "rBook(shortform of result of book)"
                Book rBook=findBook(libraryBook, dIsbn);
                //if the book founded:
                if(rBook!=null){
                    //show the confirm message which including book informations and confirmation message in yes/no option
                    int confirm=JOptionPane.showConfirmDialog(null,"Are you sure you want to delete this book?\n"+rBook.getBook(),"Confirm Deletion",JOptionPane.YES_NO_OPTION);
                    //if user choose yes, book will be removed
                    if(confirm==JOptionPane.YES_OPTION){
                        libraryBook.remove(rBook);
                        JOptionPane.showMessageDialog(null,"The book with ISBN = "+dIsbn+" is deleted.", "Delete Book", JOptionPane.PLAIN_MESSAGE); //show the success message
                    }
                }else{
                    //if the book is not founded, it will show error message
                    JOptionPane.showMessageDialog(null,"Sorry, the book with ISBN = "+dIsbn+" is not founded.", "Error", JOptionPane.ERROR_MESSAGE);
                }
                break;
            //SEARCH BOOK
            case "4":
                //ask user to input the ISBN for the book that they want to serach
                String sIsbn=JOptionPane.showInputDialog(null,"Please enter the ISBN of the book you want to search:", "Search Book", JOptionPane.QUESTION_MESSAGE);
                //if user click " x ", the system will assume it back to previous page
                if(sIsbn==null){
                    break;
                }
                //call findbook method to find the book based on the ISBN user input and then store the result in "sBook(shortform of search for the book)"
                Book sBook=findBook(libraryBook,sIsbn);
                //if the book founded, show the founded book information
                if(sBook!=null){
                    JOptionPane.showMessageDialog(null, "Book Found:\n" + sBook.getBook(),"Search Book",JOptionPane.PLAIN_MESSAGE);
                }else{
                    //if book not found, show the error message
                    JOptionPane.showMessageDialog(null, "Sorry, the book with ISBN = "+sIsbn+" is not founded.","Error",JOptionPane.ERROR_MESSAGE);
                }
                break;
            //BACK TO MAIN PAGE
            case "5":
                break;
            //handling the input that others than 1-5 and show error message
            default:
                JOptionPane.showMessageDialog(null, "Please choose the option between 1 to 5.","Error",JOptionPane.ERROR_MESSAGE);
        }
    }while(!"5".equals(cBook));//loop the method until user input "5"
}

//method for handling member's operation such as add member, update member, delete member and search member    
private static void memberManagement(ArrayList<Member> libraryMember){
    //variable to user's menu choice
    String cMember;
    //menu option for member management
    String memberMenu = "Please choose what you want to do(1-5):\n1. Add Member\n2. Update Member\n3. Delete Member\n4. Search Member\n5. Back to Main Page";
    //do-while loop to let user choose the operation repeatly unless enter 5 to exit the program
    do {
        //ask user to choose the operation from add member, update member, delete member, search memeber, 
        cMember =JOptionPane.showInputDialog(null, memberMenu, "Member Management Menu", JOptionPane.QUESTION_MESSAGE);
        //if user click "x" or "cancel", the system will assume it as option "5"
        if (cMember == null) {
            cMember = "5";
        }
        //switch to let user choose operation
        switch (cMember) {

            //ADD MEMBER
            case "1":
                //ask user to enter name
                String name = JOptionPane.showInputDialog(null,"Enter Member Name:","New Member",JOptionPane.QUESTION_MESSAGE);
                if(name == null) break;
                //ask user to enter their faculty
                String faculty = JOptionPane.showInputDialog(null,"Enter Member's Faculty:","New Member",JOptionPane.QUESTION_MESSAGE);
                if(faculty == null) break;
                //ask user to enter their student ID
                String studentId = JOptionPane.showInputDialog(null,"Enter Student ID:","New Member",JOptionPane.QUESTION_MESSAGE);
                if(studentId == null) break;
                
                //check if there is duplicate Student ID
                if (DuplicateMember(libraryMember, studentId)) {
                    JOptionPane.showMessageDialog(null, "Student ID already exists! Please enter a different ID.", "Error", JOptionPane.ERROR_MESSAGE);
                } else {
                    //add member if the student ID is not repeat
                    libraryMember.add(new Member(name, faculty, studentId));
                    JOptionPane.showMessageDialog(null, "Member Added!", "New Member", JOptionPane.PLAIN_MESSAGE);
                }
                break; 

            //UPDATE MEMBER
            case "2":
                //ask user to enter their student ID for verification
                String updateID = JOptionPane.showInputDialog(null,"Enter Student ID to update:","Update Member",JOptionPane.QUESTION_MESSAGE);
                //call the search member method
                Member uMember = findMember(libraryMember, updateID);
                
                //if the member is found
                if (uMember != null) {
                    //varaible to user's menu choice
                    String choiceU;
                    do {
                        //menu for updating member info
                        String updateMenu = uMember.getMember() +
                                "\n\nChoose what to update:\n1. Name\n2. Faculty\n3. Student ID\n4. Back";
                        //ask user to choose the operation from updating name, faculty, student ID or go back to main page
                        choiceU = JOptionPane.showInputDialog(null,updateMenu,"Update Member",JOptionPane.QUESTION_MESSAGE);
                        //if user enter any null input, system will assume as 4
                        if (choiceU == null) {
                            choiceU = "4";
                        }

                        switch (choiceU) {
                            //update member's name
                            case "1":
                                //ask user to enter new name
                                String newName = JOptionPane.showInputDialog(null,"Enter new name:","Update Member",JOptionPane.QUESTION_MESSAGE);
                                //set the update name as new name
                                uMember.setName(newName);
                                break;
                            //update member's faculty
                            case "2":
                                //ask user to enter new faculty
                                String newFaculty = JOptionPane.showInputDialog(null,"Enter new faculty:","Update Member",JOptionPane.PLAIN_MESSAGE);
                                //set the update faculty as new faculty
                                uMember.setFaculty(newFaculty);
                                break;
                            //update member's student ID
                            case "3":
                                //ask user to enter new student ID
                                String newId = JOptionPane.showInputDialog(null,"Enter new student ID:","Update Member",JOptionPane.QUESTION_MESSAGE);
                                //set the update student ID as new student ID
                                uMember.setStudentID(newId);
                                break;
                            //back to member management page
                            case "4":
                                break;

                            default:
                                //show error message with invalid choice
                                JOptionPane.showMessageDialog(null, "Invalid choice!","Error",JOptionPane.ERROR_MESSAGE);
                        }
                    } while (!"4".equals(choiceU)); //loop the method until user input "4"
                } else {
                    //if member's student ID not found, display member not found message
                    JOptionPane.showMessageDialog(null, "Member not found!","Error",JOptionPane.ERROR_MESSAGE);
                }
                break;

            //DELETE MEMBER
            case "3":
                //ask user enter the student ID resgitered
                String deleteId = JOptionPane.showInputDialog(null,"Enter Student ID to delete:","Delete Member",JOptionPane.QUESTION_MESSAGE);
                //call search member method
                Member dMember = findMember(libraryMember, deleteId);
                //if member exists
                if (dMember != null) {
                    //show confirmation message
                    int confirm = JOptionPane.showConfirmDialog(null,
                            "Are you sure?\n" + dMember.getMember(),
                            "Confirm Delete",
                            JOptionPane.YES_NO_OPTION);
                    //if confirm deletion, the member will be deleted and show a message saying member deleted
                    if (confirm == JOptionPane.YES_OPTION) {
                        libraryMember.remove(dMember);
                        JOptionPane.showMessageDialog(null, "Member deleted!","Delete Member",JOptionPane.PLAIN_MESSAGE);
                    }
                } else {
                    //if the student ID is invalid, show message saying member not found
                    JOptionPane.showMessageDialog(null, "Member not found!","Error",JOptionPane.ERROR_MESSAGE);
                }
                break;

            //SEARCH MEMBER
            case "4":
                //ask user enter the student ID resgitered
                String searchId = JOptionPane.showInputDialog(null,"Enter Student ID to search:","Search Member",JOptionPane.QUESTION_MESSAGE);
                //call search member method
                Member sMember = findMember(libraryMember, searchId);
                //if member exists
                if (sMember != null) {
                    //show confirmation message saying member founded
                    JOptionPane.showMessageDialog(null, "Member Found:\n" + sMember.getMember(),"Search Member",JOptionPane.PLAIN_MESSAGE);
                } else {
                    //if the student ID is invalid, show message saying member not found
                    JOptionPane.showMessageDialog(null, "Member not found!","Error",JOptionPane.ERROR_MESSAGE);
                }
                break;

            case "5":
                //go back to main page
                break;

            default:
                //if invalid input, error message will be display
                JOptionPane.showMessageDialog(null, "Please choose 1–5 only!","Error",JOptionPane.ERROR_MESSAGE);
        }

    }while (!"5".equals(cMember)); //loop the method until user input "5"
  }

//method for handling borrow and return book's operation such as borrow, return, view book details
private static void borrowReturnBook(ArrayList<Book>libraryBook, ArrayList<Member>libraryMember){
    String cBorrowReturn; //variable on choosing switch case
    //menu option for borrow and return books
    String borrowReturnMenu="Please choose what you want to do(1-4):\n1. Borrow Book\n2. Return Book\n3. View Book Details\n4. Back to Main Page";
    //do-while loop to let user choose their choice until enter 4 to exit this program
    do{
        //ask user to choose the operation from menu
        cBorrowReturn=JOptionPane.showInputDialog(null,borrowReturnMenu,"Borrow & Return Book",JOptionPane.QUESTION_MESSAGE);
        //if user click "x" or "cancel", the system will assume it as option "4"
        if(cBorrowReturn==null){
            cBorrowReturn="4";
        }
        //switch to let user choose operation
        switch(cBorrowReturn){
            //BORROW BOOK
            case "1":
                int cBorrow;//declare for choice to continue borrow book
                //ask user to enter student ID
                String studId=JOptionPane.showInputDialog(null,"Enter Student ID:","Student ID",JOptionPane.QUESTION_MESSAGE);
                //call member method to check if the student ID entered exist in the findmember method
                Member borrowMember=findMember(libraryMember, studId);
                //if member is found
                if(borrowMember!=null){
                    if(borrowMember.getBorrowedBooks()>=5){
                        //display limit message and show member details and titles of book borrowed
                        JOptionPane.showMessageDialog(null, "Limit Reach. Only maximum of 5 books are allowed to borrow!\n"+borrowMember.getMember()+"\n"
                            +borrowMember.getBorrowedBookTitles(),"Already Borrowed Book",JOptionPane.PLAIN_MESSAGE);
                        }
                    else{
                        do{
                            //ask user to enter book ISBN to borrow
                            String checkIsbn=JOptionPane.showInputDialog(null,"Enter Book ISBN to Borrow:","Borrow Book",JOptionPane.QUESTION_MESSAGE);
                            //call findbook method to check if ISBN entered exist in findbook method
                            Book borrow=findBook(libraryBook,checkIsbn);                  
                            //if ISBN book is found
                            if(borrow!=null){
                                //call method to check if borrowedBook was in user hand through ISBN book
                                Book sameBook=findBorrowedBook(borrowMember,checkIsbn);
                                //check if same ISBN book is found in the member borrowed book list 
                                if(sameBook!=null){
                                    //display message on book was borrowed and provide member details with book titles
                                    JOptionPane.showMessageDialog(null, "You had already borrowed this book!\n"+borrowMember.getMember()+"\n"
                                            +borrowMember.getBorrowedBookTitles(),"Borrowed Book",JOptionPane.PLAIN_MESSAGE);
                                }
                                //check the book status by calling getStatus method
                                else if(borrow.getStatus()){
                                    //set the book status to not available which shows book was borrowed
                                    borrow.setStatus(false);
                                    //add the borrowed book to the member's borrowed book list 
                                    borrowMember.getborrowedBookList().add(borrow);
                                    //declare count and call getBorrowedBooks method
                                    int count=borrowMember.getBorrowedBooks();
                                    //call setBorrowedBooks method and increase amount of book borrowed
                                    borrowMember.setBorrowedBooks(count+1);
                                    //display message and member details with borrowed book titles
                                    JOptionPane.showMessageDialog(null, "Successfully borrowed!\n"+borrowMember.getMember()+"\n"
                                            +borrowMember.getBorrowedBookTitles(),"Borrowed Book",JOptionPane.PLAIN_MESSAGE);
                                }
                                else{
                                    //display error message on book was borrowed
                                    JOptionPane.showMessageDialog(null, "Sorry, book was borrowed by others.","Error",JOptionPane.ERROR_MESSAGE);
                                }
                            }
                           else{
                            //display error message on book not found
                            JOptionPane.showMessageDialog(null, "Book not found in library!","Error",JOptionPane.ERROR_MESSAGE);
                            }
                            if (borrowMember.getBorrowedBooks() >= 5) {
                                JOptionPane.showMessageDialog(
                                    null,
                                    "Limit Reached! Only a maximum of 5 books are allowed to borrow!\n"
                                    + borrowMember.getMember() + "\n"
                                    + borrowMember.getBorrowedBookTitles(),
                                    "Borrowing Limit",
                                    JOptionPane.WARNING_MESSAGE
                                );
                                break;
                            }
                            //ask user if they want to borrow another book or not
                            cBorrow=JOptionPane.showConfirmDialog(null, "Borrow another book?","Borrow",JOptionPane.YES_NO_OPTION);
                        }while(cBorrow==JOptionPane.YES_OPTION);//when user choose yes it will loop
                    }
                }
                else{
                    //display error message on member not found
                    JOptionPane.showMessageDialog(null, "Member not found!","Error",JOptionPane.ERROR_MESSAGE);
                }
                break;
            //RETURN BOOK
            case "2":
                int cReturn;
                //ask user to enter student ID
                String studID=JOptionPane.showInputDialog(null,"Enter Student ID:","Student ID",JOptionPane.QUESTION_MESSAGE);
                //call findMember method to check if student ID exist in the method
                Member returnMember=findMember(libraryMember, studID);
                //if member is found
                if(returnMember!=null){
                    //get list of borrowed books by members
                    ArrayList<Book> borrowed=returnMember.getborrowedBookList();
                    //if the list of borrowed books is empty
                    if (borrowed.isEmpty()){
                        //display message on no book borrowed
                        JOptionPane.showMessageDialog(null, "This member hasn't borrowed any book!","Error",JOptionPane.ERROR_MESSAGE);
                        break;
                    }
                    do{
                        //if borrowed list is empty
                        if(borrowed.isEmpty()){
                            //display error message on no book to return
                            JOptionPane.showMessageDialog(null, "No More Book to Return!","Error",JOptionPane.ERROR_MESSAGE);
                            break;
                        }
                        //create a menu to let user choose the book to return
                        String returnBookMenu="Select a book to return:\n";
                        //loop the borrowed books within the size of array
                        for (int i=0;i<borrowed.size();i++){
                            //list out the borrowed book and display the book title
                            returnBookMenu+=(i+1)+". "+borrowed.get(i).getTitle()+"\n";
                        }
                        //ask user to select book number to return
                        String choiceReturnBook=JOptionPane.showInputDialog(null,returnBookMenu,"Return Book",JOptionPane.QUESTION_MESSAGE);
                        //if input is not empty
                        if(choiceReturnBook!=null){
                            //declare index and minus 1 as array start with inddex 0
                            int index=Integer.parseInt(choiceReturnBook)-1;
                            //check the index is more than equal to 0 and less than the borrowed list size
                            if(index>=0 && index<borrowed.size()){
                                //get the selected book from borrowed list
                                Book bookReturn=borrowed.get(index);
                                //remove the selected book from borrowed list
                                borrowed.remove(index);
                                //set the book status to available
                                bookReturn.setStatus(true);
                                //set the borrowed book by deducting 1 as it was returned
                                returnMember.setBorrowedBooks(returnMember.getBorrowedBooks()-1);
                                //display message and provide details on member details with borrowed book titles
                                JOptionPane.showMessageDialog(null, "Successfully Returned!\n"+returnMember.getMember()+"\n"
                                        +returnMember.getBorrowedBookTitles(),"Returned Book",JOptionPane.PLAIN_MESSAGE);
                            }
                            else{
                                //display error message
                                JOptionPane.showMessageDialog(null, "Invalid Choice!","Error",JOptionPane.ERROR_MESSAGE);
                            }
                        }
                        //ask user if they want to return another book or not
                        cReturn=JOptionPane.showConfirmDialog(null, "Return another book?","Return",JOptionPane.YES_NO_OPTION);
                    }while(cReturn==JOptionPane.YES_OPTION);//when user click yes it will loop
                }
                else{
                    //display error message on member not found
                    JOptionPane.showMessageDialog(null, "Member is not found!","Error",JOptionPane.ERROR_MESSAGE);
                    }
                break;
            //VIEW BOOK DETAILS
            case "3":
                //ask user to enter ISBN to check the book details
                String checkIsbn=JOptionPane.showInputDialog(null,"Enter Book ISBN to View Details:","Book ISBN",JOptionPane.QUESTION_MESSAGE);
                //if input is not empty
                if(checkIsbn!=null){
                    //call viewBookDetails method
                    viewBookDetails(libraryBook,checkIsbn);
                }
                else{
                    //display error message when ISBN not found
                    JOptionPane.showMessageDialog(null, "Enter a valid book ISBN!","Error",JOptionPane.ERROR_MESSAGE); 
                }
                break;
            //BACK TO MAIN PAGE
            case "4":
                break;
            //if invalid input, error message will be displayed
            default:
                //display error message and ask user to choose option given
                JOptionPane.showMessageDialog(null, "Please choose the option between 1 to 4.","Error",JOptionPane.ERROR_MESSAGE);
        }
    }while(!"4".equals(cBorrowReturn));//repeat until user enter "4" to exit the program
    }
}