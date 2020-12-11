package presenters;

import entities.Request;
import use_cases.RequestManager;

public class RequestPresenter {

    public void newRequestPrompt1(){
        System.out.println("Please type the description for your new request");
    }

    public void newRequestPrompt2(){
        System.out.println("The description you have entered is invalid. A valid description is non-empty");
    }

    public void requestingUser(Request request){
        //System.out.println(request.getRequestingUser().getUsername() + " requested the event");
    }

    public void requestText(Request request){
        System.out.println(request.getRequestText());
    }

    public void requestStatus(Request request){
        if (request.isResolved()){
            System.out.println("The request has been resolved");
        }
        else{
            System.out.println("The request has not been resolved");
        }
    }

    public void requestTextEditPrompt1(){
        System.out.println("Please type the new request text. Press enter after you are done");
    }

    public void requestTextEditPrompt2(){
        System.out.println("Please enter a non-empty request description");
    }

    public void requestTagPrompt1(){
        System.out.println("Please enter a tag for this request");
    }

    public void requestTagPrompt2(){
        System.out.println("The tag you have entered in invalid. A valid tag is a non-empty alphanumeric string");
    }

    public void requestTagPrompt3(){
        System.out.println("Please enter the tag you wish to remove");
    }

    public void requestTagPrompt4(){
        System.out.println("The tag you entered is either does not exist for the request, or is invalid.");
    }
    // TODO: 12/2/2020 add finalize adding prompts. Maybe should integrate to UI first so no wasted work is done
}
