package swordfish.space.notificationservice.listener;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.aws.messaging.config.annotation.EnableSqs;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;
import swordfish.space.notificationservice.domain.Notification;
import swordfish.space.notificationservice.services.JsonTransformService;
import swordfish.space.notificationservice.services.PusherService;

@Slf4j
@Service
@EnableSqs
public class QueueListener {

	private final PusherService pusher;
	private final JsonTransformService jsonTransformService;

	@Autowired
	public QueueListener(PusherService pusher,
			JsonTransformService jsonTransformService) {
		this.pusher = pusher;
		this.jsonTransformService = jsonTransformService;
	}

	@MessageMapping("${queues.notificationEvents}")
	public void instanceCommandHandler(String payload) {
		Notification notification = jsonTransformService.read(Notification.class,
				payload);

		pusher.push(notification.getChannel(), notification.getEvent(),
				notification.getMessage());
	}
}