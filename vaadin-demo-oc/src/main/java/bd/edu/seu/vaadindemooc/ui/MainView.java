package bd.edu.seu.vaadindemooc.ui;import com.vaadin.flow.component.html.Label;import com.vaadin.flow.component.orderedlayout.VerticalLayout;import com.vaadin.flow.router.Route;@Routepublic class MainView extends VerticalLayout {    public MainView() {        Label label = new Label("\t\t\t\t\t\t\tThis is main view...");        add(label);    }}