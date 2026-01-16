```mermaid
erDiagram
    member ||--|| profile : "1:1"
    member ||--o{ meal_plan : "manages"
    member ||--o{ diet_log : "records"
    member ||--o{ workout_log : "performs"
    member ||--o{ club : "owns"
    member ||--o{ club_member : "joins"
    member ||--o{ likes : "clicks"

    club ||--o{ club_member : "has"
    club ||--o{ board : "contains"

    board ||--o{ board_file : "attaches"
    board ||--o{ likes : "receives"

    file ||--o{ board_file : "belongs_to"
    file ||--o{ meal : "represents"

    meal ||--o{ meal_item : "consists_of"
    meal ||--o{ meal_plan : "assigned_to"
    meal ||--o{ diet_log : "logged_in"

    exercise ||--o{ workout_log : "referenced_by"

    member {
        uuid id PK
        varchar email
        varchar password
        timestamp created_at
    }

    profile {
        uuid member_id PK, FK
        varchar gender
        date birth_date
        float height
        float weight
        varchar goal
        float goal_weight
        text_array allergies
        text special_notes
    }

    meal {
        integer id PK
        varchar type
        varchar menu
        integer total_calories
        integer file_id FK
    }

    meal_item {
        integer id PK
        integer meal_id FK
        varchar name
        float amount
        integer calories
    }

    meal_plan {
        integer id PK
        uuid member_id FK
        integer meal_id FK
        date date_at
        boolean is_accepted
        timestamp created_at
    }

    diet_log {
        integer id PK
        uuid member_id FK
        integer meal_id FK
        date date_at
    }

    exercise {
        integer id PK
        varchar name
        float met
    }

    workout_log {
        integer id PK
        uuid member_id FK
        integer exercise_id FK
        integer duration_min
        integer burnt_calories
        date date_at
    }

    file {
        integer id PK
        varchar filename
        varchar org_filename
    }

    club {
        integer id PK
        varchar name
        text description
        uuid owner_id FK
    }

    club_member {
        integer id PK
        integer club_id FK
        uuid member_id FK
    }

    board {
        integer id PK
        integer club_id FK
        varchar title
        text content
        integer view_count
        integer like_count
    }

    board_file {
        integer file_id PK, FK
        integer board_id FK
    }

    likes {
        integer id PK
        integer board_id FK
        uuid member_id FK
    }
```mermaid
