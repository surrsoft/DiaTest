package ru.surrsoft.baaz.p01_db;

/**
 * columnt-constraints, см. https://www.sqlite.org/syntax/column-constraint.html
 */
public enum EFieldConstraint {
  PRIMARY_KEY,
  PRIMARY_KEY_AUTOINCREMENT,
  UNIQUE,
  OTHER;
}
