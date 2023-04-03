package com.cars.service;

import com.cars.model.Car;
import com.cars.model.Order;
import com.cars.repository.CarRepository;
import com.cars.repository.OrderRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

import java.time.Clock;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static java.time.ZoneOffset.UTC;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.InstanceOfAssertFactories.LIST;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class OrderServiceUTest {

    final LocalDateTime NOW = LocalDateTime.of(2012, 5, 13, 0, 0, 0);
    private OrderService orderService;

    private OrderRepository orderRepository = mock(OrderRepository.class);
    private CarRepository carRepository = mock(CarRepository.class);

    @BeforeEach
    void setUp() {
        orderService = new OrderService(orderRepository, carRepository, Clock.fixed(NOW.toInstant(UTC), UTC));
    }

    @Nested
    class CreateShould {

        @Test
        void apply_twenty_percent_discount_when_previous_order_less_than_one_year() {
            // given
            given(carRepository.findById(1)).willReturn(Optional.of(Car.of(1, "twingo", 10000, "Small")));
            given(orderRepository.findAll()).willReturn(List.of(
                    Order.of(1, "test@mail.com", null, 10000, LocalDate.now().minusMonths(6))));
            // when
            orderService.create("test@mail.com", 1);

            // then
            ArgumentCaptor<Order> orderArgumentCaptor = ArgumentCaptor.forClass(Order.class);
            verify(orderRepository).save(orderArgumentCaptor.capture());
            var savedOrder = orderArgumentCaptor.getValue();
            assertThat(savedOrder.getPrice()).isEqualTo(8000);
        }
    }
}
