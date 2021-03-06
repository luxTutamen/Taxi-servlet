package ua.axiom.controller.commands.viewable;

import ua.axiom.controller.Command;
import ua.axiom.core.annotations.RequestMapping;
import ua.axiom.core.annotations.Autowired;
import ua.axiom.model.UserLocale;
import ua.axiom.model.actors.Car;
import ua.axiom.model.actors.Client;
import ua.axiom.model.actors.Order;
import ua.axiom.service.GuiService;
import ua.axiom.service.LocalisationService;
import ua.axiom.service.buisness.OrderService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

@RequestMapping("/api/neworder")
public class NewOrderCommand extends Command<Client> {
    @Autowired
    private LocalisationService localisationService;
    @Autowired
    private GuiService guiService;
    @Autowired
    private OrderService orderService;

    @Override
    protected String processGet(HttpServletRequest request, HttpServletResponse response) {
        return getView();
    }

    @Override
    protected void nonUserSpecificDataFill(Map<String, Object> model, UserLocale userLocale) {

        localisationService.setLocalisedMessages(
                model,
                userLocale.toJavaLocale(),
                "word.hello",
                "word.menu",
                "sentence.new-order",
                "sentence.your-balance",
                "sentence.cancel-order",
                "sentence.promocodes",
                "sentence.replenish-balance",
                "sentence.sentence-confirm-msg",
                "sentence.new-order-request-msg",
                "sentence.delete-account",
                "info.username",
                "word.from",
                "word.to",
                "word.class",
                "word.fee",
                "word.page",
                "word.cancel",
                "word.balance",
                "sentence.your-orders",
                "sentence.order-history"
        );

        model.put("car_classes", Car.Class.values());
    }

    @Override
    protected void userSpecificDataFill(Map<String, Object> model, Client user) {
        guiService.userSpecificModelPopulation(model, user);
        model.put("new-order-details", Order.getOrderInputDescriptions());
        model.put("car-classes", Car.Class.values());
        model.put("client-balance", user.getMoney());
        model.put("username", user.getUsername());
        model.put("current-locale", user.getLocale());

        //  model.put("orders", orderService.getClientPendingOrders(user.getId()));
    }

    protected String getView() {
        return "forward:/appPages/neworder.jsp";
    }
}
