// package com.join.tab;

// import java.math.BigDecimal;
// import java.util.ArrayList;
// import java.util.List;

// import com.join.tab.domen.Category;
// import com.join.tab.domen.Item;
// import com.join.tab.domen.Order;
// import com.join.tab.domen.OrderItem;
// import com.join.tab.domen.User;

// public class Main {
// 	static Long orderItemId = 1L;

// 	public static void main(String[] args) {

// 		List<Order> orders = getEmptyListUserOrders();
// 		List<OrderItem> itemInOrder = getEmptyListItemInOneOrder();

// 		// created user
// 		User newUser = createNewUser(orders);

// 		// created category
// 		Category electronicCategory = createCategory(1L, "electronic", "EL");
// 		Category bookCategory = createCategory(2L, "book", "BOOK");

// 		// create item 
// 		Item firstItem = createItem(1L, "TV", BigDecimal.valueOf(100), electronicCategory);
// 		Item secondItem = createItem(2L, "Some book", BigDecimal.valueOf(20), bookCategory);

// 		Order userOrder = createOrder(1L, newUser, itemInOrder);

// 		addItemToUserOrder(firstItem, userOrder);
// 		addItemToUserOrder(secondItem, userOrder);
		
// 		// added created order to user list order
// 		newUser.getOrders().add(userOrder);
// 		System.out.println(newUser.getOrders().get(0).getItems().get(0).getIntem().getName());
	
// 	}

// 	/**
// 	 * Добавление товара с список товаров заказа
// 	 * @param listItemsInOrder
// 	 * @param item
// 	 * @param order
// 	 */
// 	public static void addItemToUserOrder(Item item, Order order) {
// 		OrderItem orderItem = createOrderItem(orderItemId, order, item, 1, BigDecimal.valueOf(40));
// 		order.getItems().add(orderItem);
// 		orderItemId++;


// 	}

// 	/**
// 	 * Товары заказа
// 	 * @param id 
// 	 * @param order привязка к одному заказу пользователя
// 	 * @param item один товар
// 	 * @param quantity количество товарв
// 	 * @param price цена за все количество товара
// 	 * @return
// 	 */
// 	public static OrderItem createOrderItem(Long id, Order order, Item item, int quantity, BigDecimal price) {
// 		return new OrderItem(
// 			id,
// 			order,
// 			item,
// 			quantity,
// 			price
// 		);
// 	}


// 	/**
// 	 * Создание заказа. Изачально заказ пустой. В логике приложениея он не может 
// 	 * быть пустым так как создаться при добавлении товара.
// 	 * @param id
// 	 * @param nameCategory
// 	 * @param codeCategory
// 	 * @return
// 	 */
// 	public static Order createOrder(Long id, User user, List<OrderItem> itemsInOrder) {
// 		return new Order(
// 			id,
// 			user,
// 			itemsInOrder
// 		);
// 	}
// 	public static Item createItem(Long id, String name, BigDecimal price, Category category) {
// 		return new Item(
// 					id,
// 					name,
// 					price,
// 					category
// 				);
// 	}

// 	public static Category createCategory(Long id, String nameCategory, String codeCategory){
// 		return new Category(
// 					id,
// 					nameCategory,
// 					codeCategory
// 				);
// 	}
// 	/**
// 	 * Создать пустой список заказов пользователя.
// 	 * У одного пользователя можеть быть несколько
// 	 * заказов, поэтом список заказов.
// 	 * @return List<Order>
// 	 */
// 	public static List<Order> getEmptyListUserOrders() {
// 		return new ArrayList<>();
// 	}
	
// 	/**
// 	 * Созать пустой список товаров в одном заказе.
// 	 * В одном заказе может быть несколько товаров, поэтому
// 	 * список товаров заказа.
// 	 * 
// 	 * @return List<OrderItem>
// 	 */
// 	public static List<OrderItem> getEmptyListItemInOneOrder() {
// 		return new ArrayList<>();
// 	}


// 	/**
// 	 * При создании нового пльзователя его корзина пустая.
// 	 * Так как он еще ничего не заказал.
// 	 * @param orders Пустой список
// 	 * @return Created user object
// 	 */
// 	public static User createNewUser(List <Order> orders) {
// 		return new User(
// 			1L,
// 			"John",
// 			"password",
// 			"Jogn@gmail.com",
// 			orders);
// 	}
// }
