package space.nebulark.junisockets;

import org.apache.log4j.Logger;
import org.java_websocket.WebSocket;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public interface ISignalingService {
    Logger logger = Logger.getLogger(ISignalingService.class);

    default void send(WebSocket conn, Object operation) {
        // add operation
        logger.debug("Sending");

        String jsonOperation = ((JSONObject)operation).toJSONString();

        if (conn != null) {
            // transform object to json in here
            conn.send(jsonOperation);
        } else {
            // throw new custom error
        }
    }

    default Object receive(String message) throws ParseException {

        JSONParser parser = new JSONParser();

        Object jsonObj = parser.parse(message);

        JSONObject operation = (JSONObject) jsonObj;

        // now we can access the keys with jsonObject.
        // transform json to string here
        String operationCode = (String) operation.get("opcode");
        // Add operation code here
        logger.debug("Received operation: " + operation);
        
        if (operationCode.equals(ESignalingOperationCode.KNOCK.getValue())) {
            logger.trace("Received operation knock" + operation.get("data"));

            // maybe just send operation data, create object manually
            //return new Knock((IKnockData)operation.get("opcode"));
            //return (JSONObject)operation;
        } else if (operationCode.equals(ESignalingOperationCode.OFFER.getValue())) {
            logger.trace("Received operation offer" + operationCode);

            // return new Offer(operation.data as IOfferData);
        } else if (operationCode.equals(ESignalingOperationCode.ANSWER.getValue())) {
            logger.trace("Received operation answer" + operationCode);

            // return new Answer(operation.data as IAnswerData);
        } else if (operationCode.equals(ESignalingOperationCode.CANDIDATE.getValue())) {
            logger.trace("Received operation candidate" + operationCode);

            // return new Candidate(operation.data as ICandidateData);
        } else if (operationCode.equals(ESignalingOperationCode.BIND.getValue())) {
            logger.trace("Received operation bind" + operationCode);

            // return new Bind(operation.data as IBindData);
        } else if (operationCode.equals(ESignalingOperationCode.ACCEPTING.getValue())) {
            logger.trace("Received operation accepting" + operationCode);

            // return new Accepting(operation.data as IAcceptingData);
        } else if (operationCode.equals(ESignalingOperationCode.SHUTDOWN.getValue())) {
            logger.trace("Received operation shutdown" + operationCode);

            // return new Shutdown(operation.data as IShutdownData);
        } else if (operationCode.equals(ESignalingOperationCode.CONNECT.getValue())) {
            logger.trace("Received operation connect" + operationCode);

            // return new Connect(operation.data as IConnectData);
        } else {
            // throw new custom exception
            
        }

        // Just to always return a JSONObject
        return new JSONObject();
    }
}
