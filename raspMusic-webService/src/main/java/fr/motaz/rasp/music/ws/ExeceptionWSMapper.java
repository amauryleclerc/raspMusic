package fr.motaz.rasp.music.ws;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;

public class ExeceptionWSMapper implements  ExceptionMapper<Exception> {
	 

		@Override
		public Response toResponse(Exception ex) {
			System.out.println("exception : "+ex.getMessage());
			 return Response.status(500).
				      entity(ex.getMessage()).
				      type("text/plain").
				      build();
		}
}
