package extended_parser_domain;

import magmact_domain.Formula;
import magmact_parser.VisitorOrientedParser;

import java.sql.Array;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Operation {

    private String operationID, verb, url;
    private List<String> tags;
    private List<String> requires, ensures;
    private List<Formula> pre, pos;
    private List<URLParameter> queryParams, pathParams;
    private RequestBodySchema requestBody;
    private List<Response> responses;
    private Endpoint endpoint;

    public Operation() {
    }

    public void initEndpoint() {
        endpoint = new Endpoint(url, queryParams, pathParams);
    }

    public String getOperationID() {
        return operationID;
    }

    public void setVerb(String verb) {
        this.verb = verb;
    }

    public String getVerb() {
        return verb;
    }

    public boolean isPUT() {
        return verb.equalsIgnoreCase("PUT");
    }

    public boolean isPOST() {
        return verb.equalsIgnoreCase("POST");
    }

    public boolean isGET() {
        return verb.equalsIgnoreCase("GET");
    }

    public boolean isDELETE() {
        return verb.equalsIgnoreCase("DELETE");
    }

    public boolean isPATCH() {
        return verb.equalsIgnoreCase("PATCH");
    }

    public Endpoint getEndpoint() {
        return endpoint;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUrl() {
        return url;
    }

    public List<String> getTags() {
        return tags;
    }

    public List<URLParameter> getQueryParams() {
        return queryParams;
    }

    public List<URLParameter> getPathParams() {
        return pathParams;
    }

    public RequestBodySchema getRequestBody() {
        return requestBody;
    }

    public List<String> getRequires() {
        return requires;
    }

    public List<String> getEnsures() {
        return ensures;
    }

    public List<Formula> getPre() {
        return pre;
    }

    public List<Formula> getPos() {
        return pos;
    }

    public void addPre(Formula f) {
        pre.add(f);
    }

    public void addPos(Formula f) {
        pos.add(f);
    }

    /**
     * Resets the operations pre- and post-conditions to their initial values.
     */
    public void resetContract() {
        VisitorOrientedParser parser = new VisitorOrientedParser();
        pre = new ArrayList<>();
        pos = new ArrayList<>();

        for(String r : requires)
            pre.add(parser.parse(r));

        for(String e : ensures)
            pos.add(parser.parse(e));
    }

    /**
     * Checks whether the operation is a get all.
     * E.g. GET /resources
     *
     * @return true if the operation is a get all; false otherwise.
     */
    public boolean isGetAll() {
        return isGET() && endpoint.noParameters();
    }

    /**
     * Initialises the contract's data structures (pre and pos)
     */
    public void initContracts() {
        pre = new ArrayList<>();
        pos = new ArrayList<>();
    }

    public List<Response> getResponses() {
        return responses;
    }

}
