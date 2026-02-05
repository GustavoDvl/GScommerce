package com.devsuperior.gscommerce.service.exceptions;

import java.time.Instant;

public record CustomError(Instant timestamp, Integer status, String error, String path) {


}
