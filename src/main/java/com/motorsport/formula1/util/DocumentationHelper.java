package com.motorsport.formula1.util;

public class DocumentationHelper {
  public static final String DRIVER_NOT_FOUND =
      "{\n  \"message\": \"Driver with ID 1 does not exist\",\n  \"status\": 404\n}";

  public static final String DRIVER_DELETE_SUCCESS =
      "{\n  \"message\": \"Driver deleted successfully\",\n  \"status\": 200,\n  \"data\": {\n    \"id\": 1,\n    \"name\": \"Lewis Hamilton\",\n    \"team\": \"Mercedes\",\n    \"position\": 1,\n    \"year\": 2024\n  }\n}";

  public static final String DRIVER_DELETE_ERROR =
      "{\n  \"message\": \"Error deleting driver\",\n  \"status\": 500\n}";

  public static final String PING =
      "{\n  \"message\": \"Ready to go! 🚦🏁\",\n  \"status\": 200\n}";

  public static final String INIT_SUCCESS =
      "{\n  \"message\": \"Successfully initialized driver data.\",\n  \"status\": 200\n}";

  public static final String INIT_ALREADY =
      "{\n  \"message\": \"Drivers already exist in the database. Skipping initialization.\",\n  \"status\": 409\n}";

  public static final String INIT_ERROR =
      "{\n  \"message\": \"Failed to initialize database: <error message>\",\n  \"status\": 500\n}";
}
