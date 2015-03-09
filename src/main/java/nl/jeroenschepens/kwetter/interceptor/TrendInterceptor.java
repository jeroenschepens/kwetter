package nl.jeroenschepens.kwetter.interceptor;

import java.io.Serializable;

import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;

import nl.jeroenschepens.kwetter.domain.Tweet;

@Trend
@Interceptor
public class TrendInterceptor implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1245810398944384715L;

	public TrendInterceptor() {

	}

	@AroundInvoke
	public Object test(InvocationContext invocationContext) throws Exception {
		Object[] parameters = invocationContext.getParameters();
		if (parameters.length > 0 && parameters[0] instanceof Tweet) {
			Tweet tweet = (Tweet) parameters[0];
			String content = tweet.getTweet();
			content = content.replace("vet", "dik");
			content = content.replace("cool", "hard");
			tweet.setTweet(content);
		}		
		return invocationContext.proceed();
	}
}