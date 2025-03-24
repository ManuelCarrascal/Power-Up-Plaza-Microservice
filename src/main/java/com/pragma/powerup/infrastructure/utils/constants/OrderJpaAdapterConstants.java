package com.pragma.powerup.infrastructure.utils.constants;

import java.util.List;

public class OrderJpaAdapterConstants {
    private OrderJpaAdapterConstants() {}

    public static final String STATUS_PENDING = "PENDING";
    public static final String STATUS_IN_PREPARATION = "IN_PREPARATION";
    public static final String STATUS_READY = "READY";

    public static final List<String> IN_PROGRESS_STATUSES = List.of(
            STATUS_PENDING,
            STATUS_IN_PREPARATION,
            STATUS_READY
    );
}