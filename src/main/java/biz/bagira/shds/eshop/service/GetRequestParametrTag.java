package biz.bagira.shds.eshop.service;

import javax.servlet.ServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;

/**
 * Created by Dmitriy on 19.10.2016.
 */
public class GetRequestParametrTag extends TagSupport {

    private String property;

    @Override
    public int doStartTag() throws JspException {


        ServletRequest request = pageContext.getRequest();
        String parameter = request.getParameter(property);
        try {
            pageContext.getOut().print(parameter == null ? "" : parameter);
        } catch (IOException e) {
            throw new JspException(e.getMessage());
        }
        return SKIP_BODY;
    }

    public String getProperty() {
        return property;
    }

    public void setProperty(String property) {
        this.property = property;
    }
}
