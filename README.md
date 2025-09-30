# Themed Form with TextFields and Submit Button (Compose)

**Goal**
- Build a **login form** with **username** and **password** fields.
- Style fields using **Material 3** theme **colors** and **typography**.
- Add **validation**: show **error text** if fields are empty when submitting.

## How it works
- Two `OutlinedTextField`s bound to state with `rememberSaveable`.
- Styled via `MaterialTheme.colorScheme` (focused border/label/cursor) and `MaterialTheme.typography` (title/button text).
- On **Submit**, set `isError` for empty fields and show `supportingText` with error message.
- When both fields are non-empty, show a simple "Submitted ✓" confirmation (you can replace with real login).
