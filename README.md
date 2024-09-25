# SQL to MongoQL Translator

## Project Overview
The goal of this project is to develop a tool for user interaction with MongoDB using SQL queries. The tool will allow users to write SQL queries, which will then be validated, parsed, and translated into the appropriate format (MongoQL). The translated query will be executed in the MongoDB database, and the resulting collection of documents will be organized in a structure suitable for tabular display.

## GUI
- Based on Swing, the GUI supports user interaction with the database similar to the Live SQL tool.
- Provide a workspace where the user writes code (e.g., a `TextArea`) and a separate panel to display the results of executed queries (e.g., `JTable`).

## Parser
- The parser's role is to take the string representation of SQL queries and convert it into an object.
- Define a separate class that will represent the query—this should not be a String but rather an abstraction that represents the query.

## Validator
- The created query is passed to the validator.
- The validator checks basic SQL syntax rules to ensure that further translation is feasible.

**Rules:**
- The query must have all mandatory parts.
- Everything selected that is not under an aggregate function must be included in `GROUP BY`.
- The `WHERE` clause must not contain aggregate functions.
- Table joins must have a join condition (`USING` or `ON`).

**Scenarios:**
1. If the validator detects an error, execution is halted and the user is shown a meaningful error message suggesting corrections.
2. If the query is valid, it is passed to the adapter for translation.

## Adapter
- Valid queries are passed to the adapter for translation, resulting in a `MongoQuery` object that is the object representation of a MongoQL query.
- The adapter receives the `SQLQuery` object and processes it through two phases:

1. **ParameterConverter:** Extracts variable parts of the `SQLQuery` object and prepares parameters for Mongo (e.g., if in the `SELECT` clause we have "SELECT first_name, last_name", it maps to `PROJECT`, resulting in `{"last_name": 1, "first_name": 1, "_id": 0}`).

2. **Mapper:** Takes the converted parameters and prepares the `MongoQuery`.

## Documentation Requirements
The tool supports the following from SQL, except for `INSERT`, `UPDATE`, and `DELETE` statements:
- Projection
- Sorting
- Filtering
- Logical operators
- Text and number comparisons
- `$in` operator
- Joining two or more tables
- Independent subqueries (scalar and multi-valued)
- Aggregation and grouping functions

## Additional Constraints
- Aliases in select (AS) do not need to be implemented.
- The `BETWEEN AND` operator does not need to be implemented (keep <, >, =, <=, >=, IN, LIKE, AND, OR).
- Limit `AND` and `OR` operators to 2 or 3 per query.
- `HAVING` clause does not need to be implemented.
- Subqueries can only be in the `WHERE` clause, expecting simple queries (no derived tables or correlated subqueries).
- Limit joins to a maximum of 3 tables.

## Executor and Packager
- The Executor component accepts the `MongoQuery` and executes it against the HR database on the Mongo server, resulting in a collection of unstructured documents.
- The Packager component translates the unstructured Mongo form into a structured dataset suitable for display in `JTable`.


