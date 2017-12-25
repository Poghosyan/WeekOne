package QueuesAndStacks;

import java.util.EmptyStackException;
import java.util.Queue;
import java.util.Stack;

public class HtmlValidator {
	
	public static Stack<HtmlTag> isValidHtml(Queue<HtmlTag> tags) {
        HtmlTag current, tagToMatch;
		Stack<HtmlTag> documentObjectModel = new Stack<HtmlTag>();

		while (tags.peek() != null) {
		    current = tags.remove();
		    if (current.isOpenTag()) {
                documentObjectModel.push(current);
            } else if (!current.isSelfClosing()) {
		        try {
                    tagToMatch = documentObjectModel.pop();
                } catch (EmptyStackException E) {
		            return null;
                }

		        if (!current.matches(tagToMatch)) {
                    break;
                }
            }
        }
		return documentObjectModel;
	}
	

}

