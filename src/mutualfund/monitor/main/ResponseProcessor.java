/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package mutualfund.monitor.main;

import java.util.List;
import java.util.Map;
import mutualfund.monitor.model.ResponseObjectType;
import org.jsoup.nodes.Document;

/**
 *
 * @author Chintan Kanal
 */
public interface ResponseProcessor<T> {
    Map<ResponseObjectType, List<T>> process(String schemeCode, Document response);
}
