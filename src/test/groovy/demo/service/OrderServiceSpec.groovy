package demo.service

import demo.domain.Order
import spock.lang.Specification

class OrderServiceSpec extends Specification {

    def "Testing PlaceOrder()"() {
        given: "Create order instance"
        Order order = new Order()
        OrderService orderService = OrderService.getInstance()

        and: "Initializing order"
        order.setItemName("Product Name")
                .setQuantity(2)
                .setPrice(2400)

        and: "mocking EmailService"
        EmailService emailService = Mock(EmailService)
        orderService.emailService = emailService

        when:
        orderService.placeOrder(order)

        then:
        1 * emailService.sendEmail(order)
        order.getPriceWithTex() == 480d
    }
}
