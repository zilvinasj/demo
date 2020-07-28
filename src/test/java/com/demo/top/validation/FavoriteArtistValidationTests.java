package com.demo.top.validation;

import static org.assertj.core.api.Assertions.assertThat;

import com.demo.top.domain.artist.FavoriteArtist;
import java.util.Set;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


public class FavoriteArtistValidationTests {

  private Validator validator;

  @BeforeEach
  public void setup() {
    ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
    validator = factory.getValidator();
  }

  @Test
  void givenIncorrectFavoriteArtist_whenValidate_thenCorrectValidationErrors() {
    FavoriteArtist favoriteArtist = new FavoriteArtist();
    Set<ConstraintViolation<FavoriteArtist>> violationSet = validator.validate(favoriteArtist);
    assertThat(violationSet.size()).isEqualTo(3);
  }

  @Test
  void givenCorrectFavoriteArtist_whenValidate_thenOk() {
    FavoriteArtist favoriteArtist = new FavoriteArtist();
    favoriteArtist.setAmgArtistId(1L);
    favoriteArtist.setArtistId(1L);
    favoriteArtist.setArtistName("Kanye");

    Set<ConstraintViolation<FavoriteArtist>> violationSet = validator.validate(favoriteArtist);
    assertThat(violationSet.size()).isZero();
  }






}
