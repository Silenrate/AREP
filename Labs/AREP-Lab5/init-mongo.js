db.createUser(
    {
        user:"walteros",
        pwd:"password",
        roles:[{
            role:"userAdminAnyDatabase",
            db:"arep"
        }]
    }
)
db.grantRolesToUser("walteros", [ { role: "read", db: "arep" } ])