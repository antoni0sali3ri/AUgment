package com.github.antoni0sali3ri.augment

open class RequirementFailedException(msg: String) : Exception(msg)

class PatternMismatchException(msg: String) : RequirementFailedException(msg)

class NotEnoughElementsException(msg: String) : RequirementFailedException(msg)

class OutOfRangeException(msg: String) : RequirementFailedException(msg)

class DuplicateElementException(msg: String) : RequirementFailedException(msg)
