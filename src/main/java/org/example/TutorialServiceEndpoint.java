package org.example;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import jakarta.xml.bind.JAXBElement;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import com.wstutorial.ws.tutorialservice.DeleteTutorialRequest;
import com.wstutorial.ws.tutorialservice.DeleteTutorialResponse;
import com.wstutorial.ws.tutorialservice.GetTutorialsRequest;
import com.wstutorial.ws.tutorialservice.GetTutorialsResponse;
import com.wstutorial.ws.tutorialservice.ObjectFactory;
import com.wstutorial.ws.tutorialservice.StatusCode;
import com.wstutorial.ws.tutorialservice.TutorialType;
import com.wstutorial.ws.tutorialservice.UpdateTutorialRequest;
import com.wstutorial.ws.tutorialservice.UpdateTutorialResponse;

import javax.xml.namespace.QName;

@Endpoint
public class TutorialServiceEndpoint {
    private static final String NAMESPACE_URI = "http://www.wstutorial.com/ws/TutorialService";


    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "updateTutorialRequest" )
    @ResponsePayload
    public UpdateTutorialResponse updateTutorial(@RequestPayload UpdateTutorialRequest request)throws Exception  {
        ObjectFactory factory = new ObjectFactory();
        StatusCode code = factory.createStatusCode();
        UpdateTutorialResponse response = factory.createUpdateTutorialResponse();
        code.setCode(200);
        response.setStatusCode(code);
        return response;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "deleteTutorialRequest" )
    @ResponsePayload
    public DeleteTutorialResponse deleteTutorial(@RequestPayload DeleteTutorialRequest request)throws Exception  {
        System.out.println("-->deleteTutorial<--");
        ObjectFactory factory = new ObjectFactory();
        DeleteTutorialResponse response = factory.createDeleteTutorialResponse();
        StatusCode code = factory.createStatusCode();
        code.setCode(204);
        response.setStatusCode(code);
        return response;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getTutorialsRequest")
    @ResponsePayload
    public JAXBElement<TutorialList> getTutorials(@RequestPayload JAXBElement<GetTutorialsRequest> request) throws Exception {
        TutorialList response = new TutorialList();
        response.setTutorials(getTutorials());

        // Create the JAXBElement for TutorialList
        QName responseQName = new QName(NAMESPACE_URI, "getTutorialsResponse");
        JAXBElement<TutorialList> jaxbElement = new JAXBElement<>(responseQName, TutorialList.class, response);

        return jaxbElement;
    }


//    private <T> JAXBElement createResponseJaxbElement(T object, Class clazz) {
//
//        return new JAXBElement<>(new QName(NAMESPACE_URI, clazz.getSimpleName()), clazz, object);
//    }

    private List<TutorialType> getTutorials() {
        List<TutorialType> tutorials= new ArrayList<TutorialType>();
        TutorialType tut1 = new TutorialType();
        tut1.setAuthor("John Doe");
        tut1.setId(15l);
        tut1.setName("Web Service with spring boot");

        TutorialType tut2 = new TutorialType();
        tut2.setAuthor("John Doe");
        tut2.setId(152);
        tut2.setName("Web Service with spring boot");

        tutorials.add(tut1);
        tutorials.add(tut2);
        return tutorials;
    }

}