package customExceptionHandlerPkg;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {

	 @ExceptionHandler(ProductException.class)
	    public void springHandleNotFound(HttpServletResponse response) throws IOException {
	        response.sendError(HttpStatus.NOT_FOUND.value());
	    }
	/*
	 * protected ModelAndView handleConflict1( RuntimeException ex, WebRequest
	 * request) { String bodyOfResponse = "This should be application specific";
	 * return RestResponseStatusExceptionResolver.handleIllegalArgument(ex,
	 * HttpStatus.CONFLICT);
	 * 
	 * ex, bodyOfResponse, new HttpHeaders(), HttpStatus.CONFLICT, request);
	 * 
	 * }
	 */
/*
	protected ResponseEntity<Object> handleConflict(RuntimeException ex, WebRequest request) {
		String bodyOfResponse = "This should be application specific";
		return handleExceptionInternal(ex, bodyOfResponse, new HttpHeaders(), HttpStatus.CONFLICT, request);
	}

	private ResponseEntity<Object> handleExceptionInternal(RuntimeException ex, String bodyOfResponse,
			HttpHeaders httpHeaders, HttpStatus conflict, WebRequest request) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@org.springframework.web.bind.annotation.ExceptionHandler(EntityNotFoundException.class)
	   protected ResponseEntity<Object> handleEntityNotFound(
	           EntityNotFoundException ex) {
	       ApiError apiError = new ApiError(NOT_FOUND);
	       apiError.setMessage(ex.getMessage());
	       return buildResponseEntity(apiError);
	   }*/
}
