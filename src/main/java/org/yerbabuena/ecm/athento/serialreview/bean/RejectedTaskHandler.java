package org.yerbabuena.ecm.athento.serialreview.bean;

import java.util.List;

import org.jbpm.graph.exe.ExecutionContext;
import org.nuxeo.ecm.platform.jbpm.VirtualTaskInstance;
import org.nuxeo.ecm.platform.jbpm.core.helper.RejectedTaskNotificationHandler;

public class RejectedTaskHandler extends RejectedTaskNotificationHandler {
	
	private static final long serialVersionUID = 1L;

	@SuppressWarnings("unchecked")
	@Override
	public void execute(ExecutionContext executionContext) throws Exception {
		List<String> previousActorId = (List<String>) executionContext.getVariable("previousActorId");
		List<String> realPreviousActorid = (List<String>) executionContext.getVariable("realPreviousActorId");
		
		if(realPreviousActorid != null) {
			previousActorId = realPreviousActorid;
		}
		super.execute(executionContext);
		List<VirtualTaskInstance> participants = (List<VirtualTaskInstance>) executionContext
				.getVariable("participants");
		

		VirtualTaskInstance participant = (VirtualTaskInstance) executionContext
				.getVariable("participant");
		participants.add(0, participant);
		
		executionContext.setVariable("previousActorId", previousActorId);
		executionContext.setVariable("realPreviousActorId", previousActorId);
		
		executionContext.setVariable("participants", participants);

		participants = (List<VirtualTaskInstance>) executionContext
				.getVariable("participants");
	}
}
