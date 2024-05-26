package classes

import comtufelmalikcomposekmm.UsersTb
import model.Users


fun UsersTb.toUsers(): Users {
    return Users(
        id = id,
        name = name,
        email = email,
        password = password,
        address = address,
    )
}