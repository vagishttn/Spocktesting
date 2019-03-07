package demo.service

import demo.domain.Order
import spock.lang.Specification
import spock.lang.Unroll

class OrderServiceSpec extends Specification {

    def "Testing Place Order Method"() {

        given: "Create Order and OrderService Instance"
        Order order = new Order();
        OrderService orderService = OrderService.getInstance()

        and: "Initialize Order"
        order.setItemName("Pencil")
                .setQuantity(10)
                .setPrice(10)

        and: "Mocking Object"
        EmailService emailService = Mock(EmailService)
        orderService.emailService = emailService

        when:
        orderService.placeOrder(order)

        then:
        1 * emailService.sendEmail(order)
        order.getPriceWithTex() == 21

    }

    @Unroll("testing Using Stubing: #sno")
    def "Testing Place Order Boolean Return"() {

        given: "Create Order and OrderService Instance"
        Order order = new Order()
        OrderService orderService = OrderService.getInstance()
        String cc = "dixit.vagish@gmail.com"

        and: "Initialize Order"
        order.setItemName("Pencil")
                .setQuantity(10)
                .setPrice(10)

        and: "Mocking Objects"
        EmailService emailService = Mock(EmailService)
        orderService.emailService = emailService

        when:
        boolean status = emailService.sendEmail(order, cc)

        then:
        1 * emailService.sendEmail(order, cc) >> inputStats
        order.getPriceWithTex() == 0
        status == expectedResult


        where:

        sno | inputStats | expectedResult
        1   | true       | true
        2   | false      | false


    }


}
